package cloud.ribbon.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:14
 * @since
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class RibbonProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(RibbonProviderApplication.class, args);
    }

    @GetMapping("/ribbon/provider/hello")
    public String hello(String name , HttpServletRequest request){
        return " From Port : " + request.getServerPort() + " , hello " + name;
    }
}
