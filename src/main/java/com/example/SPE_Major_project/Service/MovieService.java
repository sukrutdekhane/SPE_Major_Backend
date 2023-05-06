package com.example.SPE_Major_project.Service;

import com.example.SPE_Major_project.Entity.Movie;
import com.example.SPE_Major_project.Entity.Reviews;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MovieService {

    Movie saveMovie(Movie movie);

    List<Movie> searchMovie(String movieName);

    boolean storeReview(String movieName, String reviews);

    List<Movie> getAllMovie();

    List<Reviews> getAllReviewsByMovieName(String movieName);
}