package com.project.daechiwon.domain.community.presentation.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class CreateCommunityRequest {

    private String communityName;

    private String communityExplain;

}
