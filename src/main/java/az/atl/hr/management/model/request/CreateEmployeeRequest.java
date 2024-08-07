package az.atl.hr.management.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateEmployeeRequest {
    private String firstName;

    private String lastName;

    private LocalDateTime birthday;

    private String gender;

    private String address;

    private Long department;

    private Long position;
}
