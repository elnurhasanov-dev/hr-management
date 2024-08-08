package az.atl.hr.management.model.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER,
    ADMIN;
    @Override
    public String getAuthority() {
        return name();
    }
}
