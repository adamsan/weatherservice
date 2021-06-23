package com.codecool.weatherservice.service;

import com.codecool.weatherservice.responsemodel.WeatherData;
import org.apache.juli.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${api_key}")
    private String apiKey;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public WeatherData getCurrentWeather() {
        logger.info("Calling remote weatherservice...");
        return restTemplate.getForObject(weatherApiUrl(), WeatherData.class);
    }

    private String weatherApiUrl() {
        return "https://api.openweathermap.org/data/2.5/weather?q=Budapest&appid=" + apiKey + "&units=metric&lang=hu";
    }
}
