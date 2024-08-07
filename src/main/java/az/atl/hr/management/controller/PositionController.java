package az.atl.hr.management.controller;

import az.atl.hr.management.model.request.CreatePositionRequest;
import az.atl.hr.management.model.request.UpdatePositionRequest;
import az.atl.hr.management.model.response.PositionResponse;
import az.atl.hr.management.service.PositionService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("v1/management/positions")
@RequiredArgsConstructor
public class PositionController {
    private final PositionService positionService;

    @GetMapping
    @ResponseStatus(OK)
    public List<PositionResponse> getAllPositions() {
        return positionService.getAllPositions();
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public void createPosition(@RequestBody CreatePositionRequest request) {
        positionService.createPosition(request);
    }

    @PostMapping("/{id}")
    @ResponseStatus(NO_CONTENT)
    public void updatePosition(@PathVariable Long id, @RequestBody UpdatePositionRequest position) {
        positionService.updatePosition(id, position);
    }
}
