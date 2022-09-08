package com.project.daechiwon.domain.user.presentation.dto.response;

import com.project.daechiwon.domain.community.presentation.dto.response.CommunityResponse;
import com.project.daechiwon.domain.plan.presentation.dto.response.PlanResponse;
import com.project.daechiwon.domain.user.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter @AllArgsConstructor
@Builder
public class UserResponse {

    private String loginId;

    private String nickName;

    private UserType type;

    private List<PlanResponse> planList;

    private List<CommunityResponse> communityList;

}
