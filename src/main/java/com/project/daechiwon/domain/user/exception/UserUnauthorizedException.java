package com.project.daechiwon.domain.user.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class UserUnauthorizedException extends BusinessException {
    public UserUnauthorizedException() {
        super(HttpStatus.UNAUTHORIZED, "인증에 실패했습니다");
    }
}
