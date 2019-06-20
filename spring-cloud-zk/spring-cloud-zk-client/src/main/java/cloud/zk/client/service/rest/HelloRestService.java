package cloud.zk.client.service.rest;

import cloud.zk.client.annotation.RestClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestClient(name = "${saying.rest.service.name}")
public interface HelloRestService {

    @GetMapping("/my/hystrix/hello")
    String hello(@RequestParam("message") String message);
}
