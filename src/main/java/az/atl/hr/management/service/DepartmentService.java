package az.atl.hr.management.service;

import az.atl.hr.management.dao.entity.DepartmentEntity;
import az.atl.hr.management.dao.repository.DepartmentRepository;
import az.atl.hr.management.exception.NotFoundException;
import az.atl.hr.management.model.enums.ErrorMessage;
import az.atl.hr.management.model.request.CreateDepartmentRequest;
import az.atl.hr.management.model.request.UpdateDepartmentRequest;
import az.atl.hr.management.model.response.DepartmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.atl.hr.management.mapper.DepartmentMapper.DEPARTMENT_MAPPER;
import static az.atl.hr.management.model.enums.ErrorMessage.*;

@Service
@RequiredArgsConstructor
public class DepartmentService {
    private final DepartmentRepository departmentRepository;

    public List<DepartmentResponse> getAllDepartments() {
        return departmentRepository.findAll()
                .stream()
                .map(DEPARTMENT_MAPPER::buildDepartmentResponse)
                .collect(Collectors.toList());
    }

    public DepartmentResponse getDepartmentById(Long id) {
        var departmentEntity = fetchDepartmentIfExist(id);
        return DEPARTMENT_MAPPER.buildDepartmentResponse(departmentEntity);
    }

    public void createDepartment(CreateDepartmentRequest request) {
        departmentRepository.save(DEPARTMENT_MAPPER.buildDepartmentEntity(request));
    }

    public void updateDepartment(Long id, UpdateDepartmentRequest request) {
        var departmentEntity = fetchDepartmentIfExist(id);
        DEPARTMENT_MAPPER.updateDepartmentEntity(departmentEntity, request);
        departmentRepository.save(departmentEntity);
    }

    public void deleteDepartment(Long id) {
        departmentRepository.deleteById(id);
    }

    private DepartmentEntity fetchDepartmentIfExist(Long id) {
        return departmentRepository.findById(id).orElseThrow(
                () -> new NotFoundException(DEPARTMENT_NOT_FOUND.format(id),
                        DEPARTMENT_NOT_FOUND_CODE.getMessage())
        );
    }
}
