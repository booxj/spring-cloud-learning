package com.gateway.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 15:43
 * @since
 */
@EnableEurekaClient
@SpringBootApplication
@RestController
public class GatewayClient2Application {

    public static void main(String[] args) {
        SpringApplication.run(GatewayClient2Application.class, args);
    }

    @RequestMapping("/gateway/client2/hello")
    public String hello(String name){
        return "hi " + name;
    }

    @RequestMapping("/gateway/client2/timeout")
    public String timeout(){
        try{
            //睡5秒，网关Hystrix3秒超时
            Thread.sleep(2000);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "timeout";
    }
}
