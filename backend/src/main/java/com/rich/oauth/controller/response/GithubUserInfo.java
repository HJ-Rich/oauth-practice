package com.rich.oauth.controller.response;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class GithubUserInfo {

    private final String name;
    private final String email;
    private final String imageUrl;

    public GithubUserInfo(final String name, final String email, final String imageUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static GithubUserInfo from(Map<String, String> map) {
        return GithubUserInfo.builder()
                .name(map.get("name"))
                .email(map.get("email"))
                .imageUrl(map.get("avatar_url"))
                .build();
    }
}
