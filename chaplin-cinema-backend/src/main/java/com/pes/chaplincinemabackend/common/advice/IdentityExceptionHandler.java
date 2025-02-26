package com.pes.chaplincinemabackend.common.advice;
import com.pes.chaplincinemabackend.common.exceptions.BadRequestException;
import com.pes.chaplincinemabackend.common.exceptions.EntityAlreadyExistsException;
import com.pes.chaplincinemabackend.common.exceptions.ExceptionResponse;
import com.pes.chaplincinemabackend.auth.exceptions.InvalidPasswordException;
import com.pes.chaplincinemabackend.auth.exceptions.TokenSigningException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestControllerAdvice
@Log4j2
public class IdentityExceptionHandler {

    @ExceptionHandler(EntityAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ExceptionResponse handleEntityAlreadyExistsException(EntityAlreadyExistsException eaee,
                                                                HttpServletRequest request) {
        return ExceptionResponse.builder()
                .error(eaee.getError())
                .message(eaee.getMessage())
                .timestamp(eaee.getTimestamp())
                .path(request.getRequestURI())
                .requestId(request.getRequestId())
                .status(HttpStatus.CONFLICT.value())
                .build();
    }
    @ExceptionHandler(InvalidPasswordException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ExceptionResponse handleInvalidPasswordException(InvalidPasswordException ipe,
                                                            HttpServletRequest request) {
        return ExceptionResponse.builder()
                .error(ipe.getError())
                .message(ipe.getMessage())
                .timestamp(ipe.getTimestamp())
                .path(request.getRequestURI())
                .requestId(request.getRequestId())
                .status(HttpStatus.FAILED_DEPENDENCY.value())
                .build();
    }
    @ExceptionHandler(TokenSigningException.class)
    @ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
    public ExceptionResponse handleInvalidPasswordException(TokenSigningException tse,
                                                            HttpServletRequest request) {

        tse.printStackTrace();
        return ExceptionResponse.builder()
                .error(tse.getError())
                .message(tse.getMessage())
                .timestamp(tse.getTimestamp())
                .path(request.getRequestURI())
                .requestId(request.getRequestId())
                .status(HttpStatus.FAILED_DEPENDENCY.value())
                .stackTrace(Arrays.stream(tse.getStackTrace()).map(StackTraceElement::toString).collect(Collectors.toList()))
                .build();
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleInvalidPasswordException(BadRequestException bre,
                                                            HttpServletRequest request) {
        return ExceptionResponse.builder()
                .error(bre.getError())
                .message(bre.getMessage())
                .timestamp(bre.getTimestamp())
                .path(request.getRequestURI())
                .requestId(request.getRequestId())
                .status(HttpStatus.BAD_REQUEST.value())
                .build();
    }
}