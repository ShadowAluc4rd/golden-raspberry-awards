package com.outsera.golden_raspberry_awards_api.service;

import com.outsera.golden_raspberry_awards_api.model.Movie;
import com.outsera.golden_raspberry_awards_api.repository.MovieRepository;
import jakarta.annotation.PostConstruct;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    @PostConstruct
    public void init() {
        readCsvAndSaveMovies();
    }

    @Transactional
    public void readCsvAndSaveMovies() {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream("/movielist.csv")))) {
            String line;

            reader.readLine();

            while ((line = reader.readLine()) != null) {
                String[] values = line.split(";");
                Movie movie = new Movie();
                movie.setYear(Integer.parseInt(values[0].trim()));
                movie.setTitle(values[1].trim());
                movie.setStudios(values[2].trim());
                movie.setProducers(values[3].trim());
                if(values.length == 5) {
                    movie.setWinner(values[4].trim().equalsIgnoreCase("YES"));
                } else {
                    movie.setWinner(false);
                }
                movieRepository.save(movie);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
