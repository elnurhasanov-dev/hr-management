package az.atl.hr.management.dao.entity;

import az.atl.hr.management.model.enums.GenderModel;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.EnumType.STRING;
import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class EmployeeEntity {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate birthday;

    @Enumerated(STRING)
    private GenderModel gender;

    private String address;

    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    private DepartmentEntity department;

    @ManyToOne(fetch = LAZY)
    @ToString.Exclude
    private PositionEntity position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeEntity that = (EmployeeEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(firstName, that.firstName)
                && Objects.equals(lastName, that.lastName)
                && Objects.equals(birthday, that.birthday)
                && gender == that.gender
                && Objects.equals(address, that.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, firstName, lastName, birthday, gender, address);
    }
}
