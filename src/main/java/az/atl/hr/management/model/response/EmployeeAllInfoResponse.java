package az.atl.hr.management.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeAllInfoResponse {
    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private String gender;

    private String address;

    private String position;

    private String department;
}
