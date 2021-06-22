package com.codecool.weatherservice.service;

import com.codecool.weatherservice.responsemodel.WeatherData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${api_key}")
    private String apiKey;

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getCurrentWeather() {
        return restTemplate.getForObject(weatherApiUrl(), WeatherData.class);
    }

    private String weatherApiUrl() {
        return "https://api.openweathermap.org/data/2.5/weather?q=Budapest&appid=" + apiKey + "&units=metric&lang=hu";
    }
}
