package az.atl.hr.management.service;

import az.atl.hr.management.dao.entity.DepartmentEntity;
import az.atl.hr.management.dao.entity.EmployeeEntity;
import az.atl.hr.management.dao.entity.PositionEntity;
import az.atl.hr.management.dao.repository.EmployeeRepository;
import az.atl.hr.management.exception.NotFoundException;
import az.atl.hr.management.model.request.CreateEmployeeRequest;
import az.atl.hr.management.model.request.UpdateEmployeeAllInfoRequest;
import az.atl.hr.management.model.response.EmployeeAllInfoResponse;
import az.atl.hr.management.model.response.EmployeeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static az.atl.hr.management.mapper.EmployeeMapper.EMPLOYEE_MAPPER;
import static az.atl.hr.management.model.enums.ErrorMessage.EMPLOYEE_NOT_FOUND;
import static az.atl.hr.management.model.enums.ErrorMessage.EMPLOYEE_NOT_FOUND_CODE;

@Service
@RequiredArgsConstructor
public class EmployeeService {
    private final EmployeeRepository employeeRepository;

    public List<EmployeeAllInfoResponse> getAllInfoEmployees() {
        return employeeRepository.findAllWithDepartmentAndPosition()
                .stream()
                .map(EMPLOYEE_MAPPER::buildProductAllInfoResponse)
                .collect(Collectors.toList());
    }

    public EmployeeResponse getEmployeeById(Long id) {
        var employeeEntity = fetchEmployeeIfExist(id);
        return EMPLOYEE_MAPPER.buildProductResponse(employeeEntity);
    }

    public EmployeeAllInfoResponse getEmployeeByIdAllInfo(Long id) {
        var employeeEntity = fetchEmployeeIfExist(id);
        return EMPLOYEE_MAPPER.buildProductAllInfoResponse(employeeEntity);
    }

    public void createEmployee(CreateEmployeeRequest employee) {
        var employeeEntity = EMPLOYEE_MAPPER.buildProductEntity(employee);
        employeeEntity.setDepartment(DepartmentEntity.builder().id(employee.getDepartment()).build());
        employeeEntity.setPosition(PositionEntity.builder().id(employee.getPosition()).build());

        employeeRepository.save(employeeEntity);
    }

    public void updateEmployeeAllInfo(Long id, UpdateEmployeeAllInfoRequest request) {
        var employeeEntity = fetchEmployeeIfExist(id);
        EMPLOYEE_MAPPER.updateEmployeeEntity(employeeEntity, request);
        employeeRepository.save(employeeEntity);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    private EmployeeEntity fetchEmployeeIfExist(Long id) {
        return employeeRepository.findById(id).orElseThrow(
                () -> new NotFoundException(
                        EMPLOYEE_NOT_FOUND.format(id),
                        EMPLOYEE_NOT_FOUND_CODE.getMessage())
        );
    }
}
