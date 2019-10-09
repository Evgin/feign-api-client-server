package eg.example.feign.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

public interface WeatherApi {

    @RequestMapping(method = RequestMethod.POST, value = "/weather")
    ResponseEntity<Response> getWeather(@RequestBody Request request) throws WeatherException;

}
