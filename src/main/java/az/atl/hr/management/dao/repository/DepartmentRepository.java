package az.atl.hr.management.dao.repository;

import az.atl.hr.management.dao.entity.DepartmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentEntity, Long> {
}
