package com.rich.oauth.controller.response;

import com.rich.oauth.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class MemberResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String imageUrl;

    public MemberResponse(final Long id, final String name, final String email, final String imageUrl) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
    }

    public static MemberResponse from(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .imageUrl(member.getImageUrl())
                .build();
    }
}
