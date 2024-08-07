package az.atl.hr.management.controller;

import az.atl.hr.management.model.request.AuthRequest;
import az.atl.hr.management.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public String loginWithUsernameAndPassword(@RequestBody AuthRequest request) {
        return userService.loginWithUsernameAndPassword(request);
    }
}
