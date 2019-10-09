package eg.example.feign.controller;

import eg.example.feign.api.Request;
import eg.example.feign.api.Response;
import eg.example.feign.api.WeatherApi;
import eg.example.feign.api.WeatherException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class WeatherController implements WeatherApi {

    @Override
    public ResponseEntity<Response> getWeather(Request request) throws WeatherException {
        if (request.isNeedException()) {
            throw new WeatherException();
        } else {
            return ResponseEntity.ok(new Response(request.getId(), request.getName(), "OKAY"));
        }
    }
}