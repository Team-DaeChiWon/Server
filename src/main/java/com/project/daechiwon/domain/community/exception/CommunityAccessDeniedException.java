package com.project.daechiwon.domain.community.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class CommunityAccessDeniedException extends BusinessException {

    public CommunityAccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "해당 커뮤니티에 접근할 권한이 없습니다");
    }
}
