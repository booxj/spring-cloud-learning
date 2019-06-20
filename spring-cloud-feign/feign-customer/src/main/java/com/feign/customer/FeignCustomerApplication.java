package com.feign.customer;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:53
 * @since
 */
@EnableHystrix
@EnableFeignClients
@EnableEurekaClient
@SpringBootApplication
public class FeignCustomerApplication {

    public static void main(String[] args) {
        SpringApplication.run(FeignCustomerApplication.class, args);
    }

}
