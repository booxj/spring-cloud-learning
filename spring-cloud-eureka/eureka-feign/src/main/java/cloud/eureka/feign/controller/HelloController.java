package cloud.eureka.feign.controller;

import cloud.eureka.feign.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @Autowired
    private HelloService helloService;

    @GetMapping("hello")
    public String hello(@RequestParam(value = "name", defaultValue = "booxj") String name) {
        return helloService.hello(name);
    }
}
