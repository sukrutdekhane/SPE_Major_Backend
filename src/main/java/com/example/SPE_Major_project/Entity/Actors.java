package com.example.SPE_Major_project.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Actors {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;

    @Column(nullable = false)
    private String actorName;

    @ManyToMany(mappedBy = "actors",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Movie> movies;

}