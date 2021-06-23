package com.codecool.weatherservice;

import com.codecool.weatherservice.responsemodel.WeatherData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

/**
 * https://spring.io/guides/gs/consuming-rest/
 * http://api.openweathermap.org/data/2.5/weather?q=Budapest&appid={api_key}&units=metric&lang=hu
 */
@SpringBootApplication
@EnableCaching
@EnableScheduling
public class WeatherServiceApplication {

    @Value("${api_key}")
    private String apiKey;

    final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean
    public CommandLineRunner run(RestTemplate restTemplate) throws Exception {
        return args -> {
            final String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?q=Budapest&appid=" + apiKey + "&units=metric&lang=hu";
            final WeatherData weatherData = restTemplate.getForObject(weatherUrl, WeatherData.class);
            logger.info(weatherData.toString());
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherServiceApplication.class, args);
    }

}
