package com.outsera.golden_raspberry_awards_api.testeIntegracao;

import com.outsera.golden_raspberry_awards_api.model.ProducerResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void testGetProducersIntervals() {
        ResponseEntity<ProducerResponse> response = restTemplate.getForEntity("/api/producers/intervals", ProducerResponse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        ProducerResponse producerResponse = response.getBody();
        Assertions.assertNotNull(producerResponse);
    }

    @Test
    public void testNoWinnersScenario() {
        ResponseEntity<ProducerResponse> response = restTemplate.getForEntity("/api/producers/intervals", ProducerResponse.class);

        ProducerResponse producerResponse = response.getBody();
        Assertions.assertNotNull(producerResponse, "A resposta da API não deveria ser nula.");

        Assertions.assertNotNull(producerResponse.getMenorIntervalo(), "A lista de menor intervalo não deveria ser nula.");
        Assertions.assertFalse(producerResponse.getMenorIntervalo().isEmpty(), "A lista de menor intervalo não deveria estar vazia.");

        Assertions.assertNotNull(producerResponse.getMaiorIntervalo(), "A lista de maior intervalo não deveria ser nula.");
        Assertions.assertFalse(producerResponse.getMaiorIntervalo().isEmpty(), "A lista de maior intervalo não deveria estar vazia.");
    }


}