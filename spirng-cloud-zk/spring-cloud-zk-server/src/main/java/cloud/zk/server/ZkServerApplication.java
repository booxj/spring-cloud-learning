package cloud.zk.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class ZkServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkServerApplication.class, args);
    }
}
