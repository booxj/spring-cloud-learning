package cloud.ribbon.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:13
 * @since
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class RibbonCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonCustomerApplication.class, args);
    }

}
