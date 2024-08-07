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
@Table(name = "position")
public class PositionEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String positionName;

    @OneToMany(
            fetch = LAZY,
            mappedBy = "position"
    )
    @ToString.Exclude
    private List<EmployeeEntity> position;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PositionEntity that = (PositionEntity) o;
        return Objects.equals(id, that.id)
                && Objects.equals(positionName, that.positionName)
                && Objects.equals(position, that.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, positionName, position);
    }
}
