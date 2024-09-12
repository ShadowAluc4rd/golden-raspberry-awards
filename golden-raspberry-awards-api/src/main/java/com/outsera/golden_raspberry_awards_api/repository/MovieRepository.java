package com.outsera.golden_raspberry_awards_api.repository;

import com.outsera.golden_raspberry_awards_api.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {
}
