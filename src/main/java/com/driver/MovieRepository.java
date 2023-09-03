package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {


    Map<String,Movie> m = new HashMap<>();
    Map<String,Director> d = new HashMap<>();

    Map<Movie,Director> md = new HashMap<>();

    Map<Director,List<Movie>> directormoviedb = new HashMap<>();
    public void addMovieDirectorPair(Movie movie, Director director) {
        md.put(movie,director);
        if(directormoviedb.containsKey(director))
        {
            List<Movie> list = directormoviedb.get(director);
            list.add(movie);
            directormoviedb.put(director,list);
        }
        else
        {
            List<Movie> list = new ArrayList<>();
            list.add(movie);
            directormoviedb.put(director,list);
        }

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
        List<Movie> arr = new ArrayList<>();
        List<String> ans = new ArrayList<>();
        for(Director d : directormoviedb.keySet())
        {
            if(d.getName() == name)
            {
                arr = directormoviedb.get(d);
            }
        }

        for(Movie m : arr)
        {
            ans.add(m.getName());
        }
        return ans;
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
       d.remove(name);
       for(Movie m : md.keySet())
       {
           if(md.get(m).getName()== name) md.remove(m);
       }

       for(Director d : directormoviedb.keySet())
       {
           if(d.getName() == name)
           {
               directormoviedb.remove(d);
           }
       }
    }

    public void deleteAllDirectors() {
        md.clear();
        directormoviedb.clear();
        d.clear();
    }
}
