package com.project.daechiwon.domain.user.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class
UserDuplicatedException extends BusinessException {
    public UserDuplicatedException() {
        super(HttpStatus.CONFLICT, "이미 존재하는 아이디입니다");
    }
}
