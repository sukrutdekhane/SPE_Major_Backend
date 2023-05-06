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
public class Directors
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer directorId;

    @Column(nullable = false)
    private String directorName;

    @ManyToMany(mappedBy ="directors",fetch = FetchType.LAZY)
    @JsonBackReference
    private List<Movie> movies;
}