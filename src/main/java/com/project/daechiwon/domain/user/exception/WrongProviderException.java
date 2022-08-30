package com.project.daechiwon.domain.user.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class WrongProviderException extends BusinessException {
    public WrongProviderException() {
        super(HttpStatus.BAD_REQUEST, "잘못된 OAuth 제공자");
    }
}
