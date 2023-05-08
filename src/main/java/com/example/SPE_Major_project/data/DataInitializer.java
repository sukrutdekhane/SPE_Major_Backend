package com.example.SPE_Major_project.data;

//import com.example.SPE_Major_project.Entity.Actors;
//import com.example.SPE_Major_project.Entity.Directors;
//import com.example.SPE_Major_project.Entity.Movie;
//import com.example.SPE_Major_project.Repository.AuthenticationRepository;
//import com.example.SPE_Major_project.Repository.MovieRepository;
//import com.example.SPE_Major_project.Repository.ReviewRepository;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//import java.util.List;
//
//@Configuration
//public class DataInitializer {
//    @Bean
//    CommandLineRunner commandLineRunner(MovieRepository movieRepository) {
//        return args -> {
//
//
//            Movie movie =new Movie();
//            movie.setMovieName("Dunkirk");
//            String image = new String(Files.readAllBytes(Paths.get("data/dunkirk.txt")), StandardCharsets.UTF_8);
//            movie.setMovieImage(image);
//            movie.setMovieType("Action/Drama/History");
//            movie.setMovieLink("https://www.youtube.com/embed/T7O7BtBnsG4");
//            List<Actors> actors=new ArrayList<>();
//            Actors actors1=new Actors();
//            actors1.setActorName("Fionn Whitehead");
//            Actors actors2=new Actors();
//            actors2.setActorName("Tom Glynn-Carney");
//            Actors actors3=new Actors();
//            actors3.setActorName("Jack Lowden");
//            Actors actors4=new Actors();
//            actors4.setActorName("Harry Styles");
//            actors.add(actors1);
//            actors.add(actors2);
//            actors.add(actors3);
//            actors.add(actors4);
//            movie.setActors(actors);
//            List<Directors> directors=new ArrayList<>();
//            Directors directors1=new Directors();
//            directors1.setDirectorName("soham Nolan");
//            directors.add(directors1);
//            movie.setDirectors(directors);
//
//            movieRepository.save(movie);
//
//        };
//    }
//}
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.example.SPE_Major_project.Entity.Actors;
import com.example.SPE_Major_project.Entity.Directors;
import com.example.SPE_Major_project.Entity.Movie;
import com.example.SPE_Major_project.Repository.MovieRepository;
import com.google.common.base.Charsets;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer {

    private static final String IMAGE_PATH = "dunkirk.txt";

    @Bean
    public CommandLineRunner initializeData(MovieRepository movieRepository) {
        return args -> {
            Movie movie = new Movie();
            movie.setMovieName("sklljd");

            try {
                String image = new String(Files.readAllBytes(Paths.get(IMAGE_PATH)), StandardCharsets.UTF_8);
               // String image = Files.readLines(new File(IMAGE_PATH), Charsets.UTF_8);
                movie.setMovieImage(image);
            } catch (IOException e) {
                System.err.println("Error reading image file: " + e.getMessage());
            }

            movie.setMovieType("Action/Drama/History");
            movie.setMovieLink("https://www.youtube.com/embed/T7O7BtBnsG4");

            List<Actors> actors = new ArrayList<>();
            Actors actors1 = new Actors();
            actors1.setActorName("Fionn Whitehead");
            Actors actors2 = new Actors();
            actors2.setActorName("Tom Glynn-Carney");
            Actors actors3 = new Actors();
            actors3.setActorName("Jack Lowden");
            Actors actors4 = new Actors();
            actors4.setActorName("Harry Styles");
            actors.add(actors1);
            actors.add(actors2);
            actors.add(actors3);
            actors.add(actors4);
            movie.setActors(actors);

            List<Directors> directors = new ArrayList<>();
            Directors directors1 = new Directors();
            directors1.setDirectorName("Christopher Nolan");
            directors.add(directors1);
            movie.setDirectors(directors);

            movieRepository.save(movie);
        };
    }
}
