package com.example.SPE_Major_project.Controller;

import com.example.SPE_Major_project.Entity.Movie;
import com.example.SPE_Major_project.Entity.Reviews;
import com.example.SPE_Major_project.Service.MovieService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;
    @PostMapping("/save-movies")
    private Movie saveMovie(@RequestBody Movie movie)
    {
        return movieService.saveMovie(movie);
    }

    @GetMapping("/search-movie")
    private List<Movie> searchMovie(@RequestParam String movieName)
    {
        System.out.println(movieName);
        return movieService.searchMovie(movieName);
    }

    @GetMapping("/get-all-movie")
    private List<Movie> searchMovie()
    {
        return movieService.getAllMovie();
    }


    @PostMapping("/send-review")
    private boolean sendReview(@RequestParam String movieName, String review)
    {
        return movieService.storeReview(movieName,review);
    }

    @GetMapping("/get-all-review")
    private List<Reviews> reviewByMovieName(@RequestParam  String movieName)
    {
        return movieService.getAllReviewsByMovieName(movieName);
    }
}
