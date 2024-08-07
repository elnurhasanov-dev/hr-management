package az.atl.hr.management.mapper;

import az.atl.hr.management.dao.entity.DepartmentEntity;
import az.atl.hr.management.dao.entity.EmployeeEntity;
import az.atl.hr.management.dao.entity.PositionEntity;
import az.atl.hr.management.model.request.CreatePositionRequest;
import az.atl.hr.management.model.request.UpdateDepartmentRequest;
import az.atl.hr.management.model.request.UpdateEmployeeAllInfoRequest;
import az.atl.hr.management.model.request.UpdatePositionRequest;
import az.atl.hr.management.model.response.PositionResponse;
import org.mapstruct.*;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface PositionMapper {
    PositionMapper POSITION_MAPPER = Mappers.getMapper(PositionMapper.class);

    PositionResponse buildPositionResponse(PositionEntity entity);

    @Mapping(target = "id", ignore = true)
    PositionEntity buildPositionEntity(CreatePositionRequest request);

    @Mapping(target = "id", ignore = true)
    void updatePositionEntity(@MappingTarget PositionEntity entity, UpdatePositionRequest request);
}
