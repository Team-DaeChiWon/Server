package com.project.daechiwon.domain.feed.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class FeedNotFoundException extends BusinessException {

    public FeedNotFoundException() {
        super(HttpStatus.NOT_FOUND, "게시글을 찾지 못하였습니다");
    }
}
