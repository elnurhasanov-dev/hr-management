package az.atl.hr.management.service;

import az.atl.hr.management.dao.entity.PositionEntity;
import az.atl.hr.management.dao.repository.PositionRepository;
import az.atl.hr.management.exception.NotFoundException;
import az.atl.hr.management.model.request.CreatePositionRequest;
import az.atl.hr.management.model.request.UpdatePositionRequest;
import az.atl.hr.management.model.response.PositionResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.atl.hr.management.mapper.PositionMapper.POSITION_MAPPER;
import static az.atl.hr.management.model.enums.ErrorMessage.POSITION_NOT_FOUND;

@Service
@RequiredArgsConstructor
public class PositionService {
    private final PositionRepository positionRepository;

    public List<PositionResponse> getAllPositions() {
        return positionRepository.findAll()
                .stream()
                .map(POSITION_MAPPER::buildPositionResponse)
                .collect(Collectors.toList());
    }

    public void createPosition(CreatePositionRequest request) {
        positionRepository.save(POSITION_MAPPER.buildPositionEntity(request));
    }

    public void updatePosition(Long id, UpdatePositionRequest request) {
        var positionEntity = fetchPositionIfExist(id);
        POSITION_MAPPER.updatePositionEntity(positionEntity, request);
        positionRepository.save(positionEntity);
    }

    private PositionEntity fetchPositionIfExist(Long id) {
        return positionRepository.findById(id).orElseThrow(
                () -> new NotFoundException(POSITION_NOT_FOUND.format(id),
                        POSITION_NOT_FOUND.getMessage())
        );
    }
}
