package com.outsera.golden_raspberry_awards_api.service;

import com.outsera.golden_raspberry_awards_api.model.Movie;
import com.outsera.golden_raspberry_awards_api.model.ProducerInterval;
import com.outsera.golden_raspberry_awards_api.model.ProducerResponse;
import com.outsera.golden_raspberry_awards_api.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ProducerService {

    @Autowired
    private MovieRepository movieRepository;

    public ProducerResponse getProducersIntervals() {
        List<Movie> movies = movieRepository.findAll();
        Map<String, List<Integer>> producerWinYears = new HashMap<>();

        // Agrupa os filmes vencedores por produtor e anos
        for (Movie movie : movies) {
            if (movie.isWinner()) {
                String[] producers = movie.getProducers().split(",| and ");
                for (String producer : producers) {
                    producer = producer.trim();
                    producerWinYears.computeIfAbsent(producer, k -> new ArrayList<>()).add(movie.getYear());
                }
            }
        }

        ProducerResponse response = new ProducerResponse();
        List<ProducerInterval> minIntervals = new ArrayList<>();
        List<ProducerInterval> maxIntervals = new ArrayList<>();

        int globalMinInterval = Integer.MAX_VALUE;
        int globalMaxInterval = Integer.MIN_VALUE;

        // Calcula os intervalos de prêmios para cada produtor
        for (Map.Entry<String, List<Integer>> entry : producerWinYears.entrySet()) {
            String producer = entry.getKey();
            List<Integer> winYears = entry.getValue();
            Collections.sort(winYears);

            if (winYears.size() < 2) {
                continue;
            }

            int previousWin = winYears.get(0);

            // Percorre os anos de vitória para calcular intervalos
            for (int i = 1; i < winYears.size(); i++) {
                int followingWin = winYears.get(i);
                int interval = followingWin - previousWin;

                // Atualiza menor intervalo global
                if (interval < globalMinInterval) {
                    globalMinInterval = interval;
                    minIntervals.clear();
                    minIntervals.add(new ProducerInterval(producer, interval, previousWin, followingWin));
                } else if (interval == globalMinInterval) {
                    minIntervals.add(new ProducerInterval(producer, interval, previousWin, followingWin));
                }

                // Atualiza maior intervalo global
                if (interval > globalMaxInterval) {
                    globalMaxInterval = interval;
                    maxIntervals.clear(); // Limpa os anteriores
                    maxIntervals.add(new ProducerInterval(producer, interval, previousWin, followingWin));
                } else if (interval == globalMaxInterval) {
                    maxIntervals.add(new ProducerInterval(producer, interval, previousWin, followingWin));
                }

                previousWin = followingWin;
            }
        }

        response.setMin(minIntervals);
        response.setMax(maxIntervals);
        return response;
    }

}
