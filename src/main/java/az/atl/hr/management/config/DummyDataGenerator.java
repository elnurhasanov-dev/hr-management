package az.atl.hr.management.config;

import az.atl.hr.management.dao.entity.UserEntity;
import az.atl.hr.management.dao.repository.UserRepository;
import az.atl.hr.management.model.enums.Role;
import az.atl.hr.management.model.request.CreateUserRequest;
import az.atl.hr.management.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class DummyDataGenerator implements CommandLineRunner {
    private final UserService userService;
    private final UserRepository userRepository;

    @Override
    public void run(String... args) throws Exception {
        createDummyData();
//        call();
    }

    private void createDummyData() {
        CreateUserRequest request = CreateUserRequest.builder()
                .username("rustem")
                .password("password")
                .roles(Set.of(Role.ROLE_ADMIN))
                .build();
        userService.createUser(request);

        CreateUserRequest request2 = CreateUserRequest.builder()
                .username("elnur")
                .password("password")
//                .roles(Set.of(Role.ROLE_USER))
                .build();
        userService.createUser(request2);

    }

    private void call() {
        UserEntity user = userRepository.findByUsername("elnurr")
                .orElseThrow(() -> new RuntimeException("User not found"));
        System.out.println(user.getUsername());
        System.out.println(user.getPassword());
        System.out.println(user.getAuthorities());

    }
}
