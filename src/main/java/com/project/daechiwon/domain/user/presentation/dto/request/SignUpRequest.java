package com.project.daechiwon.domain.user.presentation.dto.request;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.project.daechiwon.domain.user.type.UserType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@NoArgsConstructor @Getter
public class SignUpRequest {

    @Length(min = 4, max = 16, message = "아이디는 4자리 이상 16자리 이하여야 합니다.")
    private String loginId;

    @Length(min = 8, max = 64, message = "비밀번호는 8자리 이상 64자리 이하여야 합니다.")
    private String password;

    @Length(min = 2, max = 10, message = "닉네임은 2자리 이상 10자리 이하여야 합니다.")
    private String nickname;

    private UserType type;
}
