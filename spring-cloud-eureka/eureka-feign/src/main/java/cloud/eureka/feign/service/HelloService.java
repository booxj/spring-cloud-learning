package cloud.eureka.feign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "service-hello", fallback = HelloServiceHystric.class)
public interface HelloService {

    @GetMapping("hello")
    String hello(@RequestParam("name") String name);
}
