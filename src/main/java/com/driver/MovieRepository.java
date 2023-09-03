package com.driver;

import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MovieRepository {


    Map<String,Movie> m = new HashMap<>();
    Map<String,Director> d = new HashMap<>();


    Map<Movie,Director> md = new HashMap<>();

    Map<Director,List<Movie>> directormoviedb = new HashMap<>();
    public boolean addMovieDirectorPair(String movieName, String directorName) {

        if(m.containsKey(movieName) && d.containsKey(directorName))
        {
            md.put(getMovieByName(movieName),getDirectorByName(directorName));
            if(directormoviedb.containsKey(getDirectorByName(directorName)))
            {
                List<Movie> m = directormoviedb.get(getDirectorByName(directorName));
                m.add(getMovieByName(movieName));
                directormoviedb.put(getDirectorByName(directorName),m);
            }
            else
            {
                List<Movie> m = new ArrayList<>();
                m.add(getMovieByName(movieName));
                directormoviedb.put(getDirectorByName(directorName),m);
            }
            return true;
        }

        return false;
    }

    public boolean addMovie(Movie movie) {
        if(m.isEmpty() || !m.containsKey(movie))
        {
            m.put(movie.getName(),movie);
            return true;
        }
       return false;
    }

    public boolean addDirector(Director director) {
        if(d.isEmpty() || !d.containsKey(director))
        {
            d.put(director.getName(),director);
            return true;
        }

        return false;
    }

    public Movie getMovieByName(String name) {

        if(m.containsKey(name))
        {
            return m.get(name);
        }
        return new Movie();

    }

    public Director getDirectorByName(String director) {
       if(d.containsKey(director))
       {
           return d.get(director);
       }
       return new Director();
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
