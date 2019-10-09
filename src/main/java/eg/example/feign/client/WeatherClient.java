package eg.example.feign.client;

import eg.example.feign.api.WeatherApi;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "data", url = "http://localhost:8080/")
public interface WeatherClient extends WeatherApi {

}
