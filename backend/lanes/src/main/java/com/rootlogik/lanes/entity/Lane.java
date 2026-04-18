package com.rootlogik.lanes.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "lanes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String originCity;

    private String destinationCity;
}
