package com.rootlogik.lanes.service.impl;

import com.rootlogik.lanes.dto.LaneRequestDto;
import com.rootlogik.lanes.entity.Lane;
import com.rootlogik.lanes.exception.ResourceNotFoundException;
import com.rootlogik.lanes.repository.LaneRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LaneServiceImplTest {

    @Mock
    private LaneRepository repository;

    @InjectMocks
    private LaneServiceImpl service;

    @Test
    void testCreateLane() {
        LaneRequestDto dto = new LaneRequestDto("LA-NYC", "LA", "NYC");
        Lane saved = Lane.builder()
                .id(1L)
                .name("LA-NYC")
                .originCity("LA")
                .destinationCity("NYC")
                .build();
        when(repository.save(any(Lane.class))).thenReturn(saved);
        var response = service.createLane(dto);
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("LA-NYC", response.getName());
        verify(repository, times(1)).save(any(Lane.class));
    }

    @Test
    void testGetLaneById_success() {
        Lane lane = Lane.builder()
                .id(1L)
                .name("Test")
                .build();

        when(repository.findById(1L)).thenReturn(Optional.of(lane));
        var result = service.getLaneById(1L);
        assertEquals("Test", result.getName());
    }

    @Test
    void testGetLaneById_notFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(ResourceNotFoundException.class,
                () -> service.getLaneById(1L));
    }

    @Test
    void testDeleteLane_success() {
        Lane lane = Lane.builder().id(1L).name("Test").build();
        when(repository.findById(1L)).thenReturn(Optional.of(lane));
        service.deleteLane(1L);
        verify(repository, times(1)).delete(lane);
    }
}
