package com.rootlogik.lanes.service.impl;

import com.rootlogik.lanes.dto.*;
import com.rootlogik.lanes.entity.Lane;
import com.rootlogik.lanes.exception.ResourceNotFoundException;
import com.rootlogik.lanes.mapper.LaneMapper;
import com.rootlogik.lanes.repository.LaneRepository;
import com.rootlogik.lanes.service.LaneService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LaneServiceImpl implements LaneService {

    private final LaneRepository repository;

    /**
     * Creates and persists a new lane.
     */
    @Override
    public LaneResponseDto createLane(LaneRequestDto dto) {
        Lane lane = LaneMapper.toEntity(dto);
        return LaneMapper.toDto(repository.save(lane));
    }

    /**
     * Retrieves all lanes from database.
     */
    @Override
    public List<LaneResponseDto> getAllLanes() {
        return repository.findAll()
                .stream()
                .map(LaneMapper::toDto)
                .collect(Collectors.toList());
    }

    /**
     * Fetches lane by ID or throws exception if not found.
     */
    @Override
    public LaneResponseDto getLaneById(Long id) {
        Lane lane = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lane not found"));
        return LaneMapper.toDto(lane);
    }

    /**
     * Updates an existing lane after validation.
     */
    @Override
    public LaneResponseDto updateLane(Long id, LaneRequestDto dto) {
        Lane lane = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lane not found"));

        lane.setName(dto.getName());
        lane.setOriginCity(dto.getOriginCity());
        lane.setDestinationCity(dto.getDestinationCity());

        return LaneMapper.toDto(repository.save(lane));
    }

    /**
     * Deletes a lane if it exists.
     */
    @Override
    public void deleteLane(Long id) {
        Lane lane = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lane not found"));

        repository.delete(lane);
    }
}
