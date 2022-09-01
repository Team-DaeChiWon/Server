package com.project.daechiwon.domain.plan.exception;

import com.project.daechiwon.global.exception.BusinessException;
import org.springframework.http.HttpStatus;

public class PlanNotFoundException extends BusinessException {
    public PlanNotFoundException() {
        super(HttpStatus.NOT_FOUND, "해당 계획을 찾을 수 없습니다");
    }
}
