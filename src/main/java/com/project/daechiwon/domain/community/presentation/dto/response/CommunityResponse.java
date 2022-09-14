package com.project.daechiwon.domain.community.presentation.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @AllArgsConstructor
@Builder
public class CommunityResponse {

    private Long communityId;

    private String communityName;

    private String communityExplain;

    private Long ownerId;

    private int number;

    private LocalDateTime createAt;

}
