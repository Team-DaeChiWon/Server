package com.project.daechiwon.global.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder @AllArgsConstructor
public class ExceptionResponse {
    private Integer code;
    private String message;
}
