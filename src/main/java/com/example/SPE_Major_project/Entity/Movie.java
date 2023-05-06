package com.example.SPE_Major_project.Entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
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
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer movieId;

    @Column(nullable = false, unique = true)
    private String movieName;

    @Column(columnDefinition = "MEDIUMTEXT")
    private String movieImage;

    private String movieLink;

    @Column(nullable = false)
    private String movieType;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name = "movie_actor",
            joinColumns = {@JoinColumn(name = "movie_id",referencedColumnName = "movieId")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id",referencedColumnName = "actorId")})
    private List<Actors> actors;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonManagedReference
    @JoinTable(name = "movie_director",
            joinColumns = {@JoinColumn(name = "movie_id",referencedColumnName = "movieId")},
            inverseJoinColumns = {@JoinColumn(name = "director_id",referencedColumnName = "directorId")})
    private List<Directors> directors;
}
