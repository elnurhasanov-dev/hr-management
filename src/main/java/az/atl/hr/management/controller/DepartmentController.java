package az.atl.hr.management.controller;

import az.atl.hr.management.model.request.CreateDepartmentRequest;
import az.atl.hr.management.model.request.UpdateDepartmentRequest;
import az.atl.hr.management.model.response.DepartmentResponse;
import az.atl.hr.management.service.DepartmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("v1/management/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public List<DepartmentResponse> getAllDepartments() {
        return departmentService.getAllDepartments();
    }

    @GetMapping("/{id}")
    @ResponseStatus(OK)
    public DepartmentResponse getDepartmentById(@PathVariable Long id) {
        return departmentService.getDepartmentById(id);
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createDepartment(@RequestBody CreateDepartmentRequest request) {
        departmentService.createDepartment(request);
    }

    @PutMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateDepartment(@PathVariable Long id,
                                 @RequestBody UpdateDepartmentRequest request) {
        departmentService.updateDepartment(id, request);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void deleteDepartment(@PathVariable Long id) {
        departmentService.deleteDepartment(id);
    }
}
