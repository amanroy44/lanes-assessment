package com.rootlogik.lanes.controller;

import com.rootlogik.lanes.dto.*;
import com.rootlogik.lanes.service.LaneService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lanes")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class LaneController {

    private final LaneService service;

    /**
     * Creates a new Lane.
     * @param dto request payload containing lane details
     * @return created Lane as response DTO
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public LaneResponseDto create(@Valid @RequestBody LaneRequestDto dto) {
        return service.createLane(dto);
    }

    /**
     * Fetches all available lanes.
     * @return list of LaneResponseDto
     */
    @GetMapping
    public List<LaneResponseDto> getAll() {
        return service.getAllLanes();
    }

    /**
     * Fetches a lane by its ID.
     * @param id lane identifier
     * @return lane details if found
     */
    @GetMapping("/{id}")
    public LaneResponseDto getById(@PathVariable Long id) {
        return service.getLaneById(id);
    }

    /**
     * Updates an existing lane.
     * @param id lane identifier to update
     * @param dto updated lane details
     * @return updated lane response
     */
    @PutMapping("/{id}")
    public LaneResponseDto update(@PathVariable Long id,
                                  @Valid @RequestBody LaneRequestDto dto) {
        return service.updateLane(id, dto);
    }

    /**
     * Deletes a lane by ID.
     * @param id lane identifier
     */
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.deleteLane(id);
    }
}
