package com.hex.wetech.core.commons.config;

import com.hex.wetech.core.models.R;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.nio.file.AccessDeniedException;

/**
 * GlobalExceptionHandler
 *
 * @author Guofeng Lin
 * @since 2023/10/20
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(AccessDeniedException.class)
    public R handleAccessDeniedException(AccessDeniedException e, HttpServletRequest request) {
        return R.error(HttpStatus.FORBIDDEN.value(), "no Auth");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R handleMethodDeniedException(MethodArgumentNotValidException e, HttpServletRequest request) {
        return R.error(e.getStatusCode(), "Unsatisfied Parameter for " + request.getRequestURI());
    }


    @ExceptionHandler(BindException.class)
    public R handleBindException(BindException e) {
        String message = e.getAllErrors().get(0).getDefaultMessage();
        return R.error(message);
    }

    @ExceptionHandler(Exception.class)
    public R handleException(Exception e, HttpServletRequest request) {
        return R.error(e.getMessage());
    }
}
