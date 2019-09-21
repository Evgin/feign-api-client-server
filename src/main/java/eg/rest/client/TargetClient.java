package eg.rest.client;

import feign.Param;
import feign.RequestLine;

public interface TargetClient {

    @RequestLine("GET /sum/{one}/{two}")
    Integer sun(@Param("one") Integer one,
                @Param("two") Integer two);

}
