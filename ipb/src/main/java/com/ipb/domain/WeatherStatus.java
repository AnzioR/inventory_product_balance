package com.ipb.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class WeatherStatus {
    Boolean isChanged;
    String presentWeather;

    public WeatherStatus(Boolean isChanged, String presentWeather) {
        this.isChanged = isChanged;
        this.presentWeather = presentWeather;
    }
}
