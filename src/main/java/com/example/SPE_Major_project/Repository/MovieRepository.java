package com.example.SPE_Major_project.Repository;

import com.example.SPE_Major_project.Entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Integer> {


    List<Movie> findByMovieNameContaining(String movieName);


    @Query("select m from Movie m")
    List<Movie> getDetailsOfAllMovies();
}
