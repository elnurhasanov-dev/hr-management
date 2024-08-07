package az.atl.hr.management.dao.repository;

import az.atl.hr.management.dao.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    @Query("SELECT e FROM EmployeeEntity e JOIN FETCH e.department d JOIN FETCH e.position p")
    List<EmployeeEntity> findAllWithDepartmentAndPosition();
}
