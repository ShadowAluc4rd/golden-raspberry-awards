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
                producerWinYears.computeIfAbsent(movie.getProducers(), k -> new ArrayList<>()).add(movie.getYear());
            }
        }

        ProducerResponse response = new ProducerResponse();
        List<ProducerInterval> minIntervals = new ArrayList<>();
        List<ProducerInterval> maxIntervals = new ArrayList<>();

        // Calcula os intervalos de prêmios para cada produtor
        for (Map.Entry<String, List<Integer>> entry : producerWinYears.entrySet()) {
            String producer = entry.getKey();
            List<Integer> winYears = entry.getValue();
            Collections.sort(winYears);  // Ordena os anos

            if (winYears.size() < 2) {
                continue;
            }

            int minInterval = Integer.MAX_VALUE;
            int maxInterval = Integer.MIN_VALUE;
            int previousWin = winYears.get(0);

            // Percorre os anos de vitória para calcular intervalos
            for (int i = 1; i < winYears.size(); i++) {
                int followingWin = winYears.get(i);
                int interval = followingWin - previousWin;

                // Atualiza menor intervalo
                if (interval < minInterval) {
                    minInterval = interval;
                    minIntervals.clear(); // Limpa os anteriores
                    minIntervals.add(new ProducerInterval(producer, minInterval, previousWin, followingWin));
                } else if (interval == minInterval) {
                    minIntervals.add(new ProducerInterval(producer, minInterval, previousWin, followingWin));
                }

                // Atualiza maior intervalo
                if (interval > maxInterval) {
                    maxInterval = interval;
                    maxIntervals.clear(); // Limpa os anteriores
                    maxIntervals.add(new ProducerInterval(producer, maxInterval, previousWin, followingWin));
                } else if (interval == maxInterval) {
                    maxIntervals.add(new ProducerInterval(producer, maxInterval, previousWin, followingWin));
                }

                previousWin = followingWin;
            }
        }

        response.setMenorIntervalo(minIntervals);
        response.setMaiorIntervalo(maxIntervals);
        return response;
    }

}
