package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {


    Map<String,Movie> m = new HashMap<>();
    Map<String,Director> d = new HashMap<>();

    Map<Movie,Director> md = new HashMap<>();
    public void addMovieDirectorPair(Movie movie, Director director) {
        md.put(movie,director);
    }

    public void addMovie(Movie movie) {
        m.put(movie.getName(),movie);
    }

    public void addDirector(Director director) {
        d.put(director.getName(),director);
    }

    public Movie getMovieByName(String name) {
        return m.get(name);
    }

    public Director getDirectorByName(String director) {
        return d.get(director);
    }

    public List<String> getMoviesByDirectorName(String name) {
        List<String> arr = new ArrayList<>();
        for(Movie m : md.keySet()) {

            Director curr = md.get(m);
            String s = curr.getName();
            if(name.equals(s)) arr.add(m.getName());
        }
        return arr;
    }

    public List<String> findAllMovies() {

        List<String> arr = new ArrayList<>();
        for(String s : m.keySet())
        {
            arr.add(s);
        }
        return arr;
    }

    public void deleteDirectorByName(String name) {
        for(Movie m : md.keySet())
        {
            Director curr = md.get(m);
            String s = curr.getName();
            if(name.equals(s))
            {
                md.remove(m);
            }
        }
    }

    public void deleteAllDirectors() {
        md.clear();
    }
}
