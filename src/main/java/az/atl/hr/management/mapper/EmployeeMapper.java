package az.atl.hr.management.mapper;

import az.atl.hr.management.dao.entity.EmployeeEntity;
import az.atl.hr.management.model.request.CreateEmployeeRequest;
import az.atl.hr.management.model.request.UpdateEmployeeAllInfoRequest;
import az.atl.hr.management.model.response.EmployeeAllInfoResponse;
import az.atl.hr.management.model.response.EmployeeResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface EmployeeMapper {
    EmployeeMapper EMPLOYEE_MAPPER = Mappers.getMapper(EmployeeMapper.class);

    EmployeeResponse buildProductResponse(EmployeeEntity product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "position", ignore = true)
    EmployeeEntity buildProductEntity(CreateEmployeeRequest request);

    @Mapping(target = "department", source = "department.departmentName")
    @Mapping(target = "position", source = "position.positionName")
    EmployeeAllInfoResponse buildProductAllInfoResponse(EmployeeEntity product);

    @Mapping(target = "department.departmentName", source = "department")
    @Mapping(target = "position.positionName", source = "position")
    void updateEmployeeEntity(@MappingTarget EmployeeEntity product, UpdateEmployeeAllInfoRequest request);
}
