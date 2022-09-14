package com.project.daechiwon.domain.notification.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class NotificationNotFoundException extends BusinessException {

    public NotificationNotFoundException() {
        super(HttpStatus.NOT_FOUND, "알림을 찾을 수 없습니다");
    }
}
