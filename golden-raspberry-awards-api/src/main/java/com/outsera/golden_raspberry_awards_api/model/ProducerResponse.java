package com.outsera.golden_raspberry_awards_api.model;

import java.util.List;

public class ProducerResponse {

    private List<ProducerInterval> min;
    private List<ProducerInterval> max;

    public List<ProducerInterval> getMin() {
        return min;
    }

    public void setMin(List<ProducerInterval> min) {
        this.min = min;
    }

    public List<ProducerInterval> getMax() {
        return max;
    }

    public void setMax(List<ProducerInterval> max) {
        this.max = max;
    }
}
