package com.rich.oauth.controller;

import com.rich.oauth.config.GithubOAuthConfig;
import com.rich.oauth.controller.response.GithubAccessTokenResponse;
import com.rich.oauth.controller.response.GithubUserInfo;
import com.rich.oauth.domain.Member;
import com.rich.oauth.domain.MemberRepository;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RequestMapping("/login")
@RestController
public class AuthController {

    private static final String GITHUB_ACCESS_TOKEN_API_URL = "https://github.com/login/oauth/access_token";
    private static final String GITHUB_USER_INFO_API_URL = "https://api.github.com/user";

    private final GithubOAuthConfig githubOAuthConfig;
    private final MemberRepository memberRepository;

    public AuthController(final GithubOAuthConfig githubOAuthConfig, final MemberRepository memberRepository) {
        this.githubOAuthConfig = githubOAuthConfig;
        this.memberRepository = memberRepository;
    }

    @GetMapping("/github")
    public Object github(@RequestParam String code) {
        WebClient webClient = WebClient.create();

        String accessToken = webClient.post()
                .uri(GITHUB_ACCESS_TOKEN_API_URL)
                .header(HttpHeaders.ACCEPT, "application/json")
                .body(Mono.just(githubOAuthConfig.createAccessTokenRequest(code)), Map.class)
                .retrieve()
                .bodyToMono(GithubAccessTokenResponse.class)
                .block()
                .getAccessToken();

        Map block = webClient.get()
                .uri(GITHUB_USER_INFO_API_URL)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + accessToken)
                .retrieve()
                .bodyToMono(Map.class)
                .block();
        GithubUserInfo from = GithubUserInfo.from(block);

        if (memberRepository.findByEmail(from.getEmail()).isEmpty()) {
            memberRepository.save(
                    Member.builder()
                            .name(from.getName())
                            .email(from.getEmail())
                            .imageUrl(from.getImageUrl())
                            .build()
            );
        }

        return accessToken;
    }
}
