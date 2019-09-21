package eg.rest.server;

import eg.rest.client.TargetClient;
import feign.Feign;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

@XSlf4j
@RestController
public class ExecutorController {

    private TargetClient targetClient;

    @PostConstruct
    public void init() {
        targetClient = Feign.builder()
                .client(new OkHttpClient())
                .encoder(new GsonEncoder())
                .decoder(new GsonDecoder())
                .target(TargetClient.class, "http://localhost:8080/");
    }

    @GetMapping("/exec/{one}/{two}")
    public ResponseEntity<Integer> sum(@PathVariable("one") Integer one,
                                       @PathVariable("two") Integer two) {
        log.entry(one, two);
        Integer sum = targetClient.sun(one, two);
        log.exit(sum);
        return ResponseEntity.ok(sum);
    }
}
