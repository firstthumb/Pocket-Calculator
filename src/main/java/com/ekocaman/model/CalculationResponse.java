package com.ekocaman.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class CalculationResponse {
    private double value;

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }
}
