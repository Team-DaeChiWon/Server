package com.project.daechiwon.domain.community.presentation.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter @NoArgsConstructor
public class CreateCommunityRequest {

    private String communityName;

    private String communityExplain;

}
