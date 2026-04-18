package com.rootlogik.lanes.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LaneRequestDto {

    @NotBlank(message = "Name is required")
    private String name;

    private String originCity;

    private String destinationCity;
}
