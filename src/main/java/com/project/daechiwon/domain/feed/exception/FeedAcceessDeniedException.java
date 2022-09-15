package com.project.daechiwon.domain.feed.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class FeedAcceessDeniedException extends BusinessException {
    public FeedAcceessDeniedException() {
        super(HttpStatus.FORBIDDEN, "해당 게시글에 접근할 권한이 없습니다");
    }
}
