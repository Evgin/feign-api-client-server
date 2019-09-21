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
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

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

    @GetMapping("/multiple/{one}/{two}")
    public ResponseEntity<Integer> exec(@PathVariable("one") Integer one,
                                        @PathVariable("two") Integer two) throws ExecutionException, InterruptedException {
        log.entry(one, two);

        CompletableFuture<Integer> first = CompletableFuture.supplyAsync(() -> targetClient.sum(one, two));
        CompletableFuture<Integer> second = CompletableFuture.supplyAsync(() -> targetClient.sum(one, two));

        return ResponseEntity.ok(first.get() + second.get());
    }

    @GetMapping("/single/{one}/{two}")
    public ResponseEntity<Integer> run(@PathVariable("one") Integer one,
                                       @PathVariable("two") Integer two) {
        log.entry(one, two);

        return ResponseEntity.ok(targetClient.sum(one, two) + targetClient.sum(one, two));
    }
}
