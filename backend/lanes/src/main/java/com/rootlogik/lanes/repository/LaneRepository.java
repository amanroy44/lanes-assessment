package com.rootlogik.lanes.repository;

import com.rootlogik.lanes.entity.Lane;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaneRepository extends JpaRepository<Lane, Long> {
}
