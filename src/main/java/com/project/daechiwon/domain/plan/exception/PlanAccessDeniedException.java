package com.project.daechiwon.domain.plan.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PlanAccessDeniedException extends BusinessException {
    public PlanAccessDeniedException() {
        super(HttpStatus.FORBIDDEN, "해당 계획에 접근할 권한이 없습니다");
    }
}
