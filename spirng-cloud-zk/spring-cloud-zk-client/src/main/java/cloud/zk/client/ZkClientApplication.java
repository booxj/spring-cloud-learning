package cloud.zk.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@SpringBootApplication
@EnableDiscoveryClient
@RestController
public class ZkClientApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZkClientApplication.class, args);
    }


    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("services")
    public String Service() {
        return discoveryClient.getServices().toString();
    }

    @RequestMapping("services/{instance}")
    public List Service(@PathVariable("instance") String instance) {

        return discoveryClient.getInstances(instance).stream().map(s -> {
            Map<String, Object> map = new HashMap<>();
            map.put("serviceId", s.getServiceId());
            map.put("host", s.getHost());
            map.put("port", s.getPort());
            return map;
        }).collect(Collectors.toList());
    }
}
