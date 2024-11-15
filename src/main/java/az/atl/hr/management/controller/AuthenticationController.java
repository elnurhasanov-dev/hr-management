package az.atl.hr.management.controller;

import az.atl.hr.management.model.request.AuthLoginRequest;
import az.atl.hr.management.model.request.AuthSignupRequest;
import az.atl.hr.management.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final UserService userService;

    @PostMapping("/login")
    public String loginWithUsernameAndPassword(@RequestBody AuthLoginRequest request) {
        return userService.loginWithUsernameAndPassword(request);
    }

    @PostMapping("/signup")
    public void signupWithUsernameAndPassword(@Valid @RequestBody AuthSignupRequest request) {
        userService.signupWithUsernameAndPassword(request);
    }
}
