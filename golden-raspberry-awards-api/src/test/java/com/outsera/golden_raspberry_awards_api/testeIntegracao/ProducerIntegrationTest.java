package com.outsera.golden_raspberry_awards_api.testeIntegracao;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.outsera.golden_raspberry_awards_api.model.ProducerResponse;
import org.json.JSONException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ProducerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetProducersIntervals() throws IOException, JSONException {
        String expectedJson = new String(Files.readAllBytes(Paths.get("src/test/resources/expected_response.json")));

        ResponseEntity<ProducerResponse> response = restTemplate.getForEntity("/api/producers/intervals", ProducerResponse.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        ProducerResponse producerResponse = response.getBody();
        Assertions.assertNotNull(producerResponse, "A resposta da API não deveria ser nula.");

        ObjectMapper objectMapper = new ObjectMapper();
        String responseJson = objectMapper.writeValueAsString(producerResponse);

        // Comparar os JSONs
        JSONAssert.assertEquals("A resposta da API não corresponde ao conteúdo esperado!", expectedJson, responseJson, JSONCompareMode.STRICT);
    }

    @Test
    public void testNoWinnersScenario() {
        ResponseEntity<ProducerResponse> response = restTemplate.getForEntity("/api/producers/intervals", ProducerResponse.class);

        ProducerResponse producerResponse = response.getBody();
        Assertions.assertNotNull(producerResponse, "A resposta da API não deveria ser nula.");

        Assertions.assertNotNull(producerResponse.getMin(), "A lista de menor intervalo não deveria ser nula.");
        Assertions.assertFalse(producerResponse.getMin().isEmpty(), "A lista de menor intervalo não deveria estar vazia.");

        Assertions.assertNotNull(producerResponse.getMax(), "A lista de maior intervalo não deveria ser nula.");
        Assertions.assertFalse(producerResponse.getMax().isEmpty(), "A lista de maior intervalo não deveria estar vazia.");
    }


}