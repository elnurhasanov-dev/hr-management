package az.atl.hr.management.service;

import az.atl.hr.management.dao.entity.UserEntity;
import az.atl.hr.management.dao.repository.UserRepository;
import az.atl.hr.management.model.request.AuthLoginRequest;
import az.atl.hr.management.model.request.AuthSignupRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import static az.atl.hr.management.model.enums.ErrorMessage.USERNAME_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class UserService {
    private final JwtService jwtService;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public void signupWithUsernameAndPassword(AuthSignupRequest request) {
        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(request.getRoles())
                .build();
        userRepository.save(newUser);
    }

    public String loginWithUsernameAndPassword(AuthLoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(request.getUsername());
        }
        throw new UsernameNotFoundException(USERNAME_NOT_FOUND.format(request.getUsername()));
    }
}
