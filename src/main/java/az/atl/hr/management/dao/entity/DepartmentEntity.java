package az.atl.hr.management.dao.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;
import java.util.Objects;

import static jakarta.persistence.FetchType.LAZY;
import static jakarta.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "department")
public class DepartmentEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String departmentName;

    @OneToMany(
            fetch = LAZY,
            mappedBy = "department"
    )
    @ToString.Exclude
    private List<EmployeeEntity> employee;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DepartmentEntity that = (DepartmentEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(departmentName, that.departmentName)
                && Objects.equals(employee, that.employee);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, departmentName, employee);
    }
}
