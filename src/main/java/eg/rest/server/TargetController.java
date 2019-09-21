package eg.rest.server;

import lombok.extern.slf4j.XSlf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@XSlf4j
@RestController
public class TargetController {

    @GetMapping("/sum/{one}/{two}")
    public ResponseEntity<Integer> sum(@PathVariable("one") Integer one,
                                       @PathVariable("two") Integer two) throws InterruptedException {
        log.entry(one, two);
        Integer sum = one + two;
        TimeUnit.MILLISECONDS.sleep(200);
        log.exit(sum);
        return ResponseEntity.ok(sum);
    }
}
