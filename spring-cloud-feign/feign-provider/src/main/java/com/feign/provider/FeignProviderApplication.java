package com.feign.provider;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:50
 * @since
 */
@SpringBootApplication
@EnableEurekaClient
@RestController
public class FeignProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignProviderApplication.class, args);
    }

    @GetMapping("/feign/provider/hello")
    public String hello(String name, HttpServletRequest request) throws InterruptedException {
        int sleep = new Random().nextInt(5);
        System.out.println(sleep);
        TimeUnit.SECONDS.sleep(sleep);
        return " From Port : " + request.getServerPort() + " , hello " + name;
    }

}
