package cloud.sleuth.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 18:29
 * @since
 */
@EnableEurekaClient
@SpringBootApplication
public class ServiceB {

    public static void main(String[] args) {
        SpringApplication.run(ServiceB.class, args);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
