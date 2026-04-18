package com.rootlogik.lanes.service;

import com.rootlogik.lanes.dto.*;

import java.util.List;

public interface LaneService {

    /**
     * Creates a new lane.
     */
    LaneResponseDto createLane(LaneRequestDto dto);

    /**
     * Retrieves all lanes.
     */
    List<LaneResponseDto> getAllLanes();

    /**
     * Retrieves a lane by ID.
     */
    LaneResponseDto getLaneById(Long id);

    /**
     * Updates an existing lane.
     */
    LaneResponseDto updateLane(Long id, LaneRequestDto dto);

    /**
     * Deletes a lane by ID.
     */
    void deleteLane(Long id);
}
