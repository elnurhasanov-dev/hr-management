package service

import az.atl.hr.management.dao.entity.DepartmentEntity
import az.atl.hr.management.dao.repository.DepartmentRepository
import az.atl.hr.management.exception.NotFoundException
import az.atl.hr.management.model.request.CreateDepartmentRequest
import az.atl.hr.management.model.request.UpdateDepartmentRequest
import az.atl.hr.management.service.DepartmentService
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static az.atl.hr.management.mapper.DepartmentMapper.DEPARTMENT_MAPPER

class DepartmentServiceTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()
    private DepartmentService departmentService
    private DepartmentRepository departmentRepository

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


    def "TestGetAllDepartments"() {
        given:
        def entities = random.nextObject(List<DepartmentEntity>)

        when:
        departmentService.getAllDepartments()

        then:
        1 * departmentRepository.findAll() >> entities
    }

    def "TestCreateDepartment"() {
        given:
        def request = random.nextObject(CreateDepartmentRequest)
        def entity = random.nextObject(DepartmentEntity)

        when:
        departmentService.createDepartment(request)

        then:
        1 * departmentRepository.save(DEPARTMENT_MAPPER.buildDepartmentEntity(request)) >> Optional.of(entity)
    }

    def "TestUpdateDepartment success"() {
        given:
        def id = random.nextLong()
        def request = random.nextObject(UpdateDepartmentRequest)
        def entity = random.nextObject(DepartmentEntity)

        when:
        departmentService.updateDepartment(id, request)

        then:
        1 * departmentRepository.findById(id) >> Optional.of(entity)
//        1 * DEPARTMENT_MAPPER.updateDepartmentEntity(entity, request)
        1 * departmentRepository.save(entity)
    }

    def "TestUpdateDepartment entity not found"() {
        given:
        def id = random.nextLong()
        def request = random.nextObject(UpdateDepartmentRequest)

        when:
        departmentService.updateDepartment(id, request)

        then:
        1 * departmentRepository.findById(id) >> Optional.empty()
        0 * departmentRepository.save()

        NotFoundException ex = thrown()
        ex.code == "DEPARTMENT_NOT_FOUND"
    }

    def "TestDeleteDepartment"() {
        given:
        def id = random.nextObject(Long)

        when:
        departmentService.deleteDepartment(id)

        then:
        1 * departmentRepository.deleteById(id)
    }
}
