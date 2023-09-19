package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/movies")
public class MovieController {


    MovieService movieService = new MovieService();


    @PostMapping("/add-movie")
     public ResponseEntity addMovie(@RequestBody Movie movie)
     {
         boolean response = movieService.addMovie(movie);
         if(response)return new ResponseEntity<>("Movie added successfully", HttpStatus.OK);
         else return  new ResponseEntity<>("Movie already present in list",HttpStatus.BAD_REQUEST);
     }


     @PostMapping("/add-director")
     public ResponseEntity addDirector(@RequestBody Director director)
     {
         boolean response = movieService.addDirector(director);
         if(response)return new ResponseEntity<>("Director added successfully",HttpStatus.OK);
         else return  new ResponseEntity<>("This Director already exist",HttpStatus.BAD_REQUEST);
     }

     @PostMapping ("/add-movie-director-pair")
     public ResponseEntity addMovieDirectorPair(@RequestParam("movie") String movieName, @RequestParam("director") String directorName)
     {
         Boolean response = movieService.addMovieDirecctorPair(movieName,directorName);
         if(response)  return new ResponseEntity<>("Movie - Director pair added successfully",HttpStatus.OK);
         else return new ResponseEntity<>("Not a valid request",HttpStatus.BAD_REQUEST);
     }


     @GetMapping("/get-movie-by-name/{name}")
     public ResponseEntity getMovieByName(@PathVariable("name") String name)
     {
         Movie m = movieService.getMovieByName(name);
         return new ResponseEntity<>(m,HttpStatus.OK);
     }

     @GetMapping("/get-director-by-name/{name}")
     public ResponseEntity getDirectorByName(@PathVariable("name") String director)
     {
         Director d = movieService.getDirectorByName(director);
         return new ResponseEntity<>(d,HttpStatus.OK);
     }


     @GetMapping("/get-movies-by-director-name/{director}")
     public ResponseEntity<List> getMoviesByDirectorName(@PathVariable("director") String name)
     {
         List<String> movie = movieService.getMoviesByDirectorName(name);
         return new ResponseEntity<>(movie,HttpStatus.OK);

     }

     @GetMapping("/get-all-movies")
     public ResponseEntity findAllMovies()
     {
         List<String> movie = movieService.findAllMovies();
         return new ResponseEntity<>(movie,HttpStatus.OK);
     }

     @DeleteMapping("/delete-director-by-name")
     public ResponseEntity deleteDirectorByName(@RequestParam("name") String name)
     {
         movieService.deleteDirectorByName(name);
         return new ResponseEntity<>("All movie of" +name+" deleted successfully",HttpStatus.OK);
     }

     @DeleteMapping("/delete-all-directors")
     public ResponseEntity deleteAllDirectors()
     {
         movieService.deleteAllDirectors();
         return new ResponseEntity<>("All directors and associated movies has been deleted",HttpStatus.OK);
     }
}
