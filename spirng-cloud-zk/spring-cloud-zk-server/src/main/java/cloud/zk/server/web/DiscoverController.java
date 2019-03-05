package cloud.zk.server.web;

import cloud.zk.server.annotation.CircuitBreaker;
import cloud.zk.server.annotation.SemaphoreCircuitBreaker;
import cloud.zk.server.config.ServerConfiguration;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.*;
import java.util.stream.Collectors;

@RestController
public class DiscoverController {

    /**************************************** 服务发现Module ****************************************/
    @Autowired
    private DiscoveryClient discoveryClient;

    @Autowired
    private ServerConfiguration serverConfiguration;

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

    @RequestMapping("hello")
    public Object hello() {
        return "hello, this message come from " + serverConfiguration.getUrl();
    }


    /**************************************** hystrix Module ****************************************/

    @GetMapping("hystrix/hello")
    @HystrixCommand(
            fallbackMethod = "errorContent",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds",
                            value = "100")
            }

    )
    public Object hystrixHello(@RequestParam(required = false) String message) throws InterruptedException {
        int waitTime = new Random().nextInt(200);
        System.out.println("helloWorld() costs " + waitTime + " ms.");

        Thread.sleep(waitTime);

        return "Hello World";
    }

    public String errorContent(String message) {
        return "Fault";
    }

    /**************************************** 自定义熔断 Module ****************************************/

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @CircuitBreaker(timeout = 100)
    @GetMapping("my/hystrix/hello")
    public Object myHystrixHello(@RequestParam(required = false) String message) throws Exception {
        return hystrixHello(message);
    }

    @SemaphoreCircuitBreaker(timeout = 100)
    @GetMapping("my/hystrix/semaphore/hello")
    public Object mySemaphoreHystrixHello(@RequestParam(required = false) String message) throws Exception {
        return hystrixHello(message);
    }
}
