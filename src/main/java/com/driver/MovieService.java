package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {


    @Autowired
    MovieRepository movieRepository;
    public String addMovie(Movie movie) {

        movieRepository.addMovie(movie);
        return "movie added successfully";
    }

    public String addDirector(Director director) {

        movieRepository.addDirector(director);
        return "director added successfully";
    }

    public String addMovieDirecctorPair(String movieName, String directorName) {

        Movie movie = getMovieByName(movieName);
        Director director = getDirectorByName(directorName);
        if(movie != null && director != null) movieRepository.addMovieDirectorPair(movie,director);
        return "movie and director paired successfully";
    }

    public Movie getMovieByName(String name) {
        return movieRepository.getMovieByName(name);
    }

    public Director getDirectorByName(String director) {
        return movieRepository.getDirectorByName(director);
    }

    public List<String> getMoviesByDirectorName(String name) {
        return movieRepository.getMoviesByDirectorName(name);
    }

    public List<String> findAllMovies() {
        return movieRepository.findAllMovies();
    }

    public String deleteDirectorByName(String name) {
         movieRepository.deleteDirectorByName(name);
         return "Director deleted successfully";
    }

    public String deleteAllDirectors() {
        movieRepository.deleteAllDirectors();
        return "All directors have been deleted";
    }
}
