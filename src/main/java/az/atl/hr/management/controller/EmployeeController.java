package az.atl.hr.management.controller;

import az.atl.hr.management.model.request.CreateEmployeeRequest;
import az.atl.hr.management.model.request.UpdateEmployeeAllInfoRequest;
import az.atl.hr.management.model.response.EmployeeAllInfoResponse;
import az.atl.hr.management.model.response.EmployeeResponse;
import az.atl.hr.management.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("v1/management/employees")
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeService employeeService;

    @GetMapping("/all-info")
    public List<EmployeeAllInfoResponse> getAllInfoEmployees() {
        return employeeService.getAllInfoEmployees();
    }

    @GetMapping("/{id}")
    public EmployeeResponse getEmployeeById(@PathVariable Long id) {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/all-info/{id}")
    public EmployeeAllInfoResponse getEmployeeByIdAllInfo(@PathVariable Long id) {
        return employeeService.getEmployeeByIdAllInfo(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createEmployee(@RequestBody CreateEmployeeRequest employee) {
        employeeService.createEmployee(employee);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateEmployeeAllInfo(@PathVariable Long id, @RequestBody UpdateEmployeeAllInfoRequest employee) {
        employeeService.updateEmployeeAllInfo(id, employee);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
    }
}
