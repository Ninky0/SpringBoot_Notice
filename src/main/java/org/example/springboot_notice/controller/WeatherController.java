package org.example.springboot_notice.controller;

import lombok.RequiredArgsConstructor;
import org.example.springboot_notice.dto.Region;
import org.example.springboot_notice.dto.article.ArticleResponseDTO;
import org.example.springboot_notice.dto.weather.WeatherResponse;
import org.example.springboot_notice.service.WeatherService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/weather")
public class WeatherController {
    private final WeatherService weatherService;

    @GetMapping
    public String getWeatherData(Model model){
        model.addAttribute("weather", weatherService.getWeatherData(60,127));
        // System.out.println(weatherService.getWeatherData(60,127));
        return "weather_weather";
    }

    @GetMapping("/regions")
    @ResponseBody
    public List<Region> getRegionList() {
        return weatherService.getRegionsFromCSV();
    }

    @GetMapping("/search")
    public ResponseEntity searchWeather(@RequestParam int nx, @RequestParam int ny) {
        WeatherResponse weather = weatherService.getWeatherData(nx, ny);
        return ResponseEntity.ok(weather);
    }

}
