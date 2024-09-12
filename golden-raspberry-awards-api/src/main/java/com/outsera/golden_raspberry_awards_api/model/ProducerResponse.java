package com.outsera.golden_raspberry_awards_api.model;

import java.util.List;

public class ProducerResponse {

    private List<ProducerInterval> menorIntervalo;
    private List<ProducerInterval> maiorIntervalo;



    public List<ProducerInterval> getMenorIntervalo() {
        return menorIntervalo;
    }

    public void setMenorIntervalo(List<ProducerInterval> menorIntervalo) {
        this.menorIntervalo = menorIntervalo;
    }

    public List<ProducerInterval> getMaiorIntervalo() {
        return maiorIntervalo;
    }

    public void setMaiorIntervalo(List<ProducerInterval> maiorIntervalo) {
        this.maiorIntervalo = maiorIntervalo;
    }
}
