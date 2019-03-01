package cloud.zk.client.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

@RestController
public class HelloController {

    @Value("${server.name}")
    private String serverName;

    private static List<String> urls = new ArrayList<>();

    @Scheduled(fixedDelay = 30 * 1000)
    public void updateUrls() {
        List<String> newUrls = new ArrayList<>();
        for (ServiceInstance instance : discoveryClient.getInstances(serverName)) {
            newUrls.add(instance.getHost() + ":" + instance.getPort());
        }
        this.urls = newUrls;
    }


    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private DiscoveryClient discoveryClient;

    @RequestMapping("hello")
    public String hello(HttpServletRequest request) {
        String url;
        int index = new Random().nextInt(urls.size());
        if (request.isSecure()) {
            url = "https://" + urls.get(index) + "//" + "hello";
        } else {
            url = "http://" + urls.get(index) + "//" + "hello";
        }

        return restTemplate.getForObject(url, String.class);
    }
}
