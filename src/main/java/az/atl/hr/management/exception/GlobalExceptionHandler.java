package az.atl.hr.management.exception;

import az.atl.hr.management.model.response.ExceptionResponse;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.security.SignatureException;

import static az.atl.hr.management.model.enums.ErrorMessage.*;
import static org.springframework.http.HttpStatus.*;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ExpiredJwtException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handle(ExpiredJwtException ex) {
        log.error("ExpiredJwtException : " + ex);
        return new ExceptionResponse(EXPIRED_JWT_EXCEPTION_CODE.getMessage(),
                EXPIRED_JWT_EXCEPTION.getMessage());
    }

    @ExceptionHandler(SignatureException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handle(SignatureException ex) {
        log.error("SignatureException : " + ex);
        return new ExceptionResponse(INVALID_JWT_SIGNATURE_CODE.getMessage(),
                INVALID_JWT_SIGNATURE.getMessage());
    }

    @ExceptionHandler(MalformedJwtException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handle(MalformedJwtException ex) {
        log.error("MalformedJwtException : " + ex);
        return new ExceptionResponse(MALFORMED_JWT_EXCEPTION_CODE.getMessage(),
                MALFORMED_JWT_EXCEPTION.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handle(IllegalArgumentException ex) {
        log.error("IllegalArgumentException  : " + ex);
        return new ExceptionResponse(ILLEGAL_ARGUMENT_EXCEPTION_CODE.getMessage(),
                ILLEGAL_ARGUMENT_EXCEPTION.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    @ResponseStatus(UNAUTHORIZED)
    public ExceptionResponse handle(BadCredentialsException ex) {
        log.error("BadCredentialsException : " + ex);
        return new ExceptionResponse(WRONG_PASSWORD_OR_USERNAME_CODE.getMessage(),
                WRONG_PASSWORD_OR_USERNAME.getMessage());
    }

    @ExceptionHandler(AccessDeniedException.class)
    @ResponseStatus(FORBIDDEN)
    public ExceptionResponse handle(AccessDeniedException ex) {
        log.error("AccessDeniedException : " + ex);
        return new ExceptionResponse(ACCESS_DENIED_CODE.getMessage(),
                ACCESS_DENIED.getMessage());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(NOT_FOUND)
    public ExceptionResponse handle(NotFoundException ex) {
        log.error("NotFoundException : " + ex);
        return new ExceptionResponse(ex.getCode(), ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    public ExceptionResponse handle(Exception ex) {
        log.error("Exception :", ex);
        return new ExceptionResponse(UNEXPECTED_EXCEPTION_CODE.getMessage(),
                UNEXPECTED_EXCEPTION.getMessage());
    }
}

//    @ExceptionHandler(AccountStatusException.class)
//    @ResponseStatus(FORBIDDEN)
//    public ExceptionResponse handle(AccountStatusException ex) {
//        log.error("AccountStatusException : " + ex);
//        return new ExceptionResponse("ACCOUNT_IS_LOCKED", "The account is locked");}


