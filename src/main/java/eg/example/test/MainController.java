package eg.example.test;

import eg.example.feign.api.Request;
import eg.example.feign.api.WeatherException;
import eg.example.feign.client.WeatherClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class MainController {

    private final WeatherClient weatherClient;

    @GetMapping("/test")
    public ResponseEntity test(@RequestParam("ex") boolean needEx) {
        try {
            return weatherClient.getWeather(new Request(10, "Jack", needEx));
        } catch (WeatherException e) {
            log.error("got weather ex", e);
            return ResponseEntity.badRequest().build();
        }
    }
}
