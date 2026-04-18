package com.rootlogik.lanes.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LaneResponseDto {

    private Long id;
    private String name;
    private String originCity;
    private String destinationCity;
}
