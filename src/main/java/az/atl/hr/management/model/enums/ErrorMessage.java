package az.atl.hr.management.model.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorMessage {
    EMPLOYEE_NOT_FOUND_CODE("EMPLOYEE_NOT_FOUND"),
    EMPLOYEE_NOT_FOUND("Employee not found with id: %d"),

    DEPARTMENT_NOT_FOUND_CODE("DEPARTMENT_NOT_FOUND"),
    DEPARTMENT_NOT_FOUND("Department not found with id: %d"),

    POSITION_NOT_FOUND_CODE("POSITION_NOT_FOUND"),
    POSITION_NOT_FOUND("Position not found with id: %d"),

    MALFORMED_JWT_EXCEPTION_CODE("MALFORMED_JWT_EXCEPTION"),
    MALFORMED_JWT_EXCEPTION("JWT is improperly formatted or structurally invalid"),

    EXPIRED_JWT_EXCEPTION_CODE("EXPIRED_JWT_EXCEPTION"),
    EXPIRED_JWT_EXCEPTION("The JWT token has expired"),

    INVALID_JWT_SIGNATURE_CODE("INVALID_JWT_SIGNATURE"),
    INVALID_JWT_SIGNATURE("The JWT token has expired"),

    ILLEGAL_ARGUMENT_EXCEPTION_CODE("ILLEGAL_ARGUMENT_EXCEPTION"),
    ILLEGAL_ARGUMENT_EXCEPTION("Not appropriate or valid for its expected input"),

    WRONG_PASSWORD_OR_USERNAME_CODE("WRONG_PASSWORD_OR_USERNAME"),
    WRONG_PASSWORD_OR_USERNAME("The username or password is incorrect"),

    ACCESS_DENIED_CODE("ACCESS_DENIED"),
    ACCESS_DENIED("You are not authorized to access this resource"),

    UNEXPECTED_EXCEPTION_CODE("UNEXPECTED_EXCEPTION"),
    UNEXPECTED_EXCEPTION("Unknown internal server error"),

    USERNAME_NOT_FOUND("Username not found with name: %s");

    private final String message;

    public String format(Object... args) {
        return String.format(message, args);
    }
}
