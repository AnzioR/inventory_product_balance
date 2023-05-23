package com.ipb.domain;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class WeatherStatus {
    Boolean isChanged;
    String presentWeather;
    String icon;

    public WeatherStatus(Boolean isChanged, String presentWeather, String icon) {
        this.isChanged = isChanged;
        this.presentWeather = presentWeather;
    }
}
