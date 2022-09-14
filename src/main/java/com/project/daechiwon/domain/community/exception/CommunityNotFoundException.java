package com.project.daechiwon.domain.community.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CommunityNotFoundException extends BusinessException {

    public CommunityNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 커뮤니티를 찾을 수 없습니다");
    }
}
