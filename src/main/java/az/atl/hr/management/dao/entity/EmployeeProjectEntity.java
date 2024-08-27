package az.atl.hr.management.dao.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

import static jakarta.persistence.CascadeType.ALL;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee_project")
public class EmployeeProjectEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String projectHandled;

    private LocalDateTime dateStarted;

    private LocalDateTime dateEnded;

    @ManyToOne(cascade = ALL)
    @ToString.Exclude
    private EmployeeEntity employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EmployeeProjectEntity that = (EmployeeProjectEntity) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
