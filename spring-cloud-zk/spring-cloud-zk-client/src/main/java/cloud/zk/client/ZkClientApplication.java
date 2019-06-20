package cloud.zk.client;

import cloud.zk.client.annotation.EnableRestClient;
import cloud.zk.client.service.feign.HelloService;
import cloud.zk.client.service.rest.HelloRestService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableDiscoveryClient
@EnableScheduling
@EnableFeignClients(clients = HelloService.class) // 引入 @FeignClient
@EnableRestClient(clients = HelloRestService.class) // 引入 @RestClient
public class ZkClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkClientApplication.class, args);
    }

}
