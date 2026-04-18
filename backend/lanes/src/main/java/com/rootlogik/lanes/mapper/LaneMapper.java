package com.rootlogik.lanes.mapper;

import com.rootlogik.lanes.dto.*;
import com.rootlogik.lanes.entity.Lane;

public class LaneMapper {

    public static Lane toEntity(LaneRequestDto dto) {
        return Lane.builder()
                .name(dto.getName())
                .originCity(dto.getOriginCity())
                .destinationCity(dto.getDestinationCity())
                .build();
    }

    public static LaneResponseDto toDto(Lane lane) {
        return LaneResponseDto.builder()
                .id(lane.getId())
                .name(lane.getName())
                .originCity(lane.getOriginCity())
                .destinationCity(lane.getDestinationCity())
                .build();
    }
}
