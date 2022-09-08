package com.project.daechiwon.domain.community.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CommunityAlreadyExistsException extends BusinessException {

    public CommunityAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "해당 커뮤니티는 존재합니다");
    }
}
