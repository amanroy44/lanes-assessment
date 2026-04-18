package com.rootlogik.lanes.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rootlogik.lanes.dto.LaneRequestDto;
import com.rootlogik.lanes.dto.LaneResponseDto;
import com.rootlogik.lanes.service.LaneService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(LaneController.class)
class LaneControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private LaneService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testCreateLane() throws Exception {

        LaneRequestDto request = new LaneRequestDto("LA-NYC", "LA", "NYC");

        LaneResponseDto response = LaneResponseDto.builder()
                .id(1L)
                .name("LA-NYC")
                .originCity("LA")
                .destinationCity("NYC")
                .build();

        Mockito.when(service.createLane(Mockito.any())).thenReturn(response);

        mockMvc.perform(post("/api/lanes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("LA-NYC"));
    }

    @Test
    void testGetAllLanes() throws Exception {

        Mockito.when(service.getAllLanes())
                .thenReturn(List.of(
                        LaneResponseDto.builder().id(1L).name("A").build()
                ));

        mockMvc.perform(get("/api/lanes"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("A"));
    }
}
