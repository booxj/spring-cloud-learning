package cloud.sleuth.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

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
public class ServiceC {

    public static void main(String[] args) {
        SpringApplication.run(ServiceC.class, args);
    }

}
