package az.atl.hr.management.service;

import az.atl.hr.management.dao.entity.UserEntity;
import az.atl.hr.management.dao.repository.UserRepository;
import az.atl.hr.management.model.request.AuthRequest;
import az.atl.hr.management.model.request.CreateUserRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
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

    public void createUser(CreateUserRequest request) {
        UserEntity newUser = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .authorities(request.getRoles())
                .build();
        userRepository.save(newUser);
    }

    public String loginWithUsernameAndPassword(AuthRequest request) {
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
