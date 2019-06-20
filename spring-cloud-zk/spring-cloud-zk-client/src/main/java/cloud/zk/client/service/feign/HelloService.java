package cloud.zk.client.service.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "spring-cloud-zk-server")
public interface HelloService {

    @GetMapping("/my/hystrix/hello")
    String hello(@RequestParam("message") String message);
}
