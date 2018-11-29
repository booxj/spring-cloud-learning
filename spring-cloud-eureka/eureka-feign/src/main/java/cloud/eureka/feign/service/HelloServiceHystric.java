package cloud.eureka.feign.service;

import org.springframework.stereotype.Component;

@Component
public class HelloServiceHystric implements HelloService {

    @Override
    public String hello(String name) {
        return "sorry " + name;
    }
}
