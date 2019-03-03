package cloud.zk.client.web;

import cloud.zk.client.annotation.CustomizedLoadBalanced;
import cloud.zk.client.loadbalance.LoadBalanceRequestInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@RestController
public class HelloController {

//    private volatile Set<String> urls = new HashSet<>();
//
//    @Scheduled(fixedDelay = 30 * 1000)
//    public void updateUrls() {
//        Set<String> oldUrls = this.urls;
//        List<ServiceInstance> serviceInstances = discoveryClient.getInstances(serverName);
//
//        Set<String> newUrls = serviceInstances.stream().map(s ->
//                s.isSecure() ?
//                        "https://" + s.getHost() + ":" + s.getPort() :
//                        "http://" + s.getHost() + ":" + s.getPort()
//
//        ).collect(Collectors.toSet());
//        this.urls = newUrls;
//        oldUrls.clear();
//    }


    @Autowired
    @CustomizedLoadBalanced
    private RestTemplate restTemplate;

    @Autowired
    @LoadBalanced
    private RestTemplate lbRestTemplate;


    @RequestMapping("/invoke/{serverName}/hello")
    public String hello(@PathVariable String serverName) {

        return restTemplate.getForObject(serverName + "/hello", String.class);
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
    public Object customizer(@Qualifier Collection<RestTemplate> restTemplates, ClientHttpRequestInterceptor interceptor) {
        restTemplates.forEach(r -> {
            r.setInterceptors(Arrays.asList(interceptor));
        });
        return new Object();
    }

}
