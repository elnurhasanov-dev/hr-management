package az.atl.hr.management.model.request;

import az.atl.hr.management.model.enums.Role;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AuthSignupRequest {
    @NotEmpty(message = "Username is required!")
    @Size(min = 2, max = 20, message = "Please enter a username between 2 and 20 characters")
    private String username;

    @NotEmpty(message = "Password is required!")
    @Size(min = 6, max = 20, message = "Please enter a password between 6 and 20 characters")
    private String password;

    private Set<Role> roles;
}
