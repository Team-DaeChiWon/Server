package com.project.daechiwon.domain.notification.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotificationAlreadyExistsException extends BusinessException {
    public NotificationAlreadyExistsException() {
        super(HttpStatus.CONFLICT, "같은 내용의 알림이 이미 있습니다");
    }
}
