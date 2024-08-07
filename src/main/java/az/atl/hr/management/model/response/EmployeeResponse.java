package az.atl.hr.management.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private String gender;

    private String address;
}
