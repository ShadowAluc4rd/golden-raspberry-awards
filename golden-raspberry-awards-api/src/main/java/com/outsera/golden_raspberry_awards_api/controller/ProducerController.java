package com.outsera.golden_raspberry_awards_api.controller;

import com.outsera.golden_raspberry_awards_api.model.ProducerResponse;
import com.outsera.golden_raspberry_awards_api.service.ProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/producers")
public class ProducerController {

    @Autowired
    private ProducerService producerService;

    @GetMapping("/intervals")
    public ResponseEntity<ProducerResponse> getProducersIntervals() {
        ProducerResponse response = producerService.getProducersIntervals();
        return ResponseEntity.ok(response);
    }

}
