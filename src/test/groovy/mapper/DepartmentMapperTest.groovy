package mapper

import az.atl.hr.management.model.request.CreateDepartmentRequest
import io.github.benas.randombeans.EnhancedRandomBuilder
import io.github.benas.randombeans.api.EnhancedRandom
import spock.lang.Specification

import static az.atl.hr.management.mapper.DepartmentMapper.DEPARTMENT_MAPPER

class DepartmentMapperTest extends Specification {
    private EnhancedRandom random = EnhancedRandomBuilder.aNewEnhancedRandom()

    def "TestBuildDepartmentEntity"() {
        given:
        def request = random.nextObject(CreateDepartmentRequest)

        when:
        def actual = DEPARTMENT_MAPPER.buildDepartmentEntity(request)

        then:
        actual.departmentName == request.departmentName
    }
}