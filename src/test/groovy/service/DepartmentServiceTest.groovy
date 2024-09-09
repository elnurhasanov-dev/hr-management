package service

import az.atl.hr.management.dao.entity.DepartmentEntity
import az.atl.hr.management.dao.repository.DepartmentRepository
import az.atl.hr.management.exception.NotFoundException
import az.atl.hr.management.service.DepartmentService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

class DepartmentServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private DepartmentRepository departmentRepository
    private DepartmentService departmentService

    def setup() {
        departmentRepository = Mock()
        departmentService = new DepartmentService(departmentRepository)
    }

    def "TestGetDepartmentById success"() {
        given:
        def id = random.nextObject(Long)
        def entity = random.nextObject(DepartmentEntity)

        when:
        def actual = departmentService.getDepartmentById(id)

        then:
        1 * departmentRepository.findById(id) >> Optional.of(entity)
        actual.id == entity.id
        actual.departmentName == entity.departmentName
    }

    def "TestGetDepartmentById entity not found"() {
        given:
        def id = random.nextObject(Long)

        when:
        departmentService.getDepartmentById(id)

        then:
        1 * departmentRepository.findById(id) >> Optional.empty()

        NotFoundException ex = thrown()
        ex.code == "DEPARTMENT_NOT_FOUND"
//        thrown(NotFoundException)
    }
}
