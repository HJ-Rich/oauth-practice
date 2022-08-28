package com.rich.oauth.config;

import java.util.Map;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@ConstructorBinding
@ConfigurationProperties(prefix = "rich.oauth.github")
public class GithubOAuthConfig {

    private final String clientId;
    private final String clientSecret;

    public GithubOAuthConfig(final String clientId, final String clientSecret) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
    }

    public Map<String, String> createAccessTokenRequest(final String code) {
        return Map.of(
                "client_id", clientId,
                "client_secret", clientSecret,
                "code", code
        );
    }
}
