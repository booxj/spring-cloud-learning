package cloud.zk.client.web;

import cloud.zk.client.annotation.CustomizedLoadBalanced;
import cloud.zk.client.loadbalance.LoadBalanceRequestInterceptor;
import cloud.zk.client.service.feign.HelloService;
import cloud.zk.client.service.rest.HelloRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class HelloController {

    /******************************* 基于Ribbons实现负载均衡 *******************************/
    //自定义实现负载均衡
    @Autowired
    @CustomizedLoadBalanced
    private RestTemplate restTemplate;

    //基于ribbon实现负载均衡
    @Autowired
    @LoadBalanced
    private RestTemplate lbRestTemplate;

    @RequestMapping("/invoke/{serverName}/hello")
    public String hello(@PathVariable String serverName) {
        return restTemplate.getForObject(serverName + "/hello", String.class);
    }

    @RequestMapping("/lb/hello")
    public String lbhello() {
        return lbRestTemplate.getForObject("http://spring-cloud-zk-server/hello", String.class);
    }

    @Bean
    public ClientHttpRequestInterceptor interceptor() {
        return new LoadBalanceRequestInterceptor();
    }

    @Bean
    @CustomizedLoadBalanced
    public RestTemplate restTemplate(ClientHttpRequestInterceptor interceptor) {
        RestTemplate restTemplate = new RestTemplate();

        //增加拦截器
//        restTemplate.setInterceptors(Arrays.asList(interceptor));
        return restTemplate;
    }

    @Bean
    @LoadBalanced
    public RestTemplate lbRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public Object customizer(@CustomizedLoadBalanced Collection<RestTemplate> restTemplates, ClientHttpRequestInterceptor interceptor) {
        restTemplates.forEach(r -> {
            r.setInterceptors(Arrays.asList(interceptor));
        });
        return new Object();
    }


    /******************************* 基于Feign实现负载均衡 *******************************/
    @Autowired
    private HelloService helloService;

    @Autowired
    private HelloRestService helloRestService;

    @GetMapping("/feign/hello")
    public String feignSay(@RequestParam String message) {
        return helloService.hello(message);
    }

    @GetMapping("/rest/hello")
    public String restSay(@RequestParam String message) {
        return helloRestService.hello(message);
    }

}
