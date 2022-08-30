package com.project.daechiwon.global.exception;

import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Log4j2
@RestControllerAdvice
public class ExceptionAdvice {

    /**
     * 일반적인 비즈니스 로직 예외를 처리합니다
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ExceptionResponse> businessException(BusinessException exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .code(exception.getStatus().value())
                .message(exception.getMessage())
                .build(),
                exception.getStatus());
    }

    /**
     * Bad Request 예외를 처리합니다
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionResponse> badRequestException(MethodArgumentNotValidException exception) {
        return new ResponseEntity<>(ExceptionResponse.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .build(),
                HttpStatus.BAD_REQUEST);
    }

    /**
     * 처리되지 아니한 예외를 처리합니다
     * @param exception
     * @return ResponseEntity
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ExceptionResponse> unHandledException(RuntimeException exception) {
        log.error(exception.getMessage());

        return new ResponseEntity<>(ExceptionResponse.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("알 수 없는 문제가 발생하였습니다.")
                .build(),
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
