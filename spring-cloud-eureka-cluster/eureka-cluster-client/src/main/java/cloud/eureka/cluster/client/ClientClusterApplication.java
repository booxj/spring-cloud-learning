package cloud.eureka.cluster.client;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

public class ClientClusterApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientClusterApplication.class, args);
    }

    @RequestMapping("/hello")
    public String home(@RequestParam(value = "name", defaultValue = "booxj") String name) {
        return "hello " + name;
    }
}
