package com.example.SPE_Major_project.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Reviews
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer Id;

    @Column(nullable = false)
    private String movieName;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String review;

}