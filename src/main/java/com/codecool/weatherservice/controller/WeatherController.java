package com.codecool.weatherservice.controller;

import com.codecool.weatherservice.responsemodel.WeatherData;
import com.codecool.weatherservice.service.WeatherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public WeatherData showWeather() {
        return weatherService.getCurrentWeather();
    }
}
