package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/movies")
public class MovieController {

    @Autowired
    MovieService movieService;


    @PostMapping("/add-movie")
     public ResponseEntity addMovie(@RequestBody Movie movie)
     {
         String response = movieService.addMovie(movie);
         return ResponseEntity.ok(response);
     }


     @PostMapping("/add-director")
     public ResponseEntity addDirector(@RequestBody Director director)
     {
         String response = movieService.addDirector(director);
         return ResponseEntity.ok(response);
     }

     @PutMapping("/add-movie-director-pair")
     public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName)
     {
         String response = movieService.addMovieDirecctorPair(movieName,directorName);
         return ResponseEntity.ok(response);
     }


     @GetMapping("/get-movie-by-name/{name}")
     public ResponseEntity getMovieByName(@PathVariable("name") String name)
     {
         Movie m = movieService.getMovieByName(name);
         return ResponseEntity.ok(m);
     }

     @GetMapping("/get-director-by-name/{name}")
     public ResponseEntity getDirectorByName(@PathVariable("name") String director)
     {
         Director d = movieService.getDirectorByName(director);
         return ResponseEntity.ok(d);
     }


     @GetMapping("/get-movies-by-director-name/{director}")
     public ResponseEntity<List> getMoviesByDirectorName(@PathVariable("director") String name)
     {
         List<String> movie = movieService.getMoviesByDirectorName(name);
         return ResponseEntity.ok(movie);

     }

     @GetMapping("/get-all-movies")
     public ResponseEntity findAllMovies()
     {
         List<String> movie = movieService.findAllMovies();
         return ResponseEntity.ok(movie);
     }

     @DeleteMapping("/delete-director-by-name")
     public ResponseEntity deleteDirectorByName(@RequestParam("name") String name)
     {
         String response = movieService.deleteDirectorByName(name);
         return ResponseEntity.ok(response);
     }

     @DeleteMapping("/delete-all-directors")
     public ResponseEntity deleteAllDirectors()
     {
         String response = movieService.deleteAllDirectors();
         return ResponseEntity.ok(response);
     }
}
