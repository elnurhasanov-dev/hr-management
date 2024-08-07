package az.atl.hr.management.mapper;

import az.atl.hr.management.dao.entity.DepartmentEntity;
import az.atl.hr.management.model.request.CreateDepartmentRequest;
import az.atl.hr.management.model.request.UpdateDepartmentRequest;
import az.atl.hr.management.model.response.DepartmentResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface DepartmentMapper {

    DepartmentMapper DEPARTMENT_MAPPER = Mappers.getMapper(DepartmentMapper.class);

    DepartmentResponse buildDepartmentResponse(DepartmentEntity entity);

    @Mapping(target = "id", ignore = true)
    DepartmentEntity buildDepartmentEntity(CreateDepartmentRequest request);

    @Mapping(target = "id", ignore = true)
    void updateDepartmentEntity(@MappingTarget DepartmentEntity entity, UpdateDepartmentRequest request);
}
