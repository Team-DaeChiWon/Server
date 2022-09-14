package com.project.daechiwon.domain.community.presentation.dto.response;

import com.project.daechiwon.domain.user.type.UserType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter @AllArgsConstructor
@Builder
public class OwnerResponse {

    private String nickName;

    private UserType type;

}
