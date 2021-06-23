package com.codecool.weatherservice.service;

import com.codecool.weatherservice.responsemodel.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    @Value("${api_key}")
    private String apiKey;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Cacheable("weather")
    public WeatherData getCurrentWeather() {
        logger.info("Calling remote weatherservice...");
        return restTemplate.getForObject(weatherApiUrl(), WeatherData.class);
    }

    @Scheduled(fixedRate = 1000 * 30)
    @CacheEvict("weather")
    public void cleanUpCache() {
        logger.info("I will try to cleanup the cache!");
    }

    private String weatherApiUrl() {
        return "https://api.openweathermap.org/data/2.5/weather?q=Budapest&appid=" + apiKey + "&units=metric&lang=hu";
    }
}
