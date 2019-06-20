package cloud.ribbon.customer.config;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:18
 * @since
 */
@Configuration
public class RibbonLBConfig {

    //使用随机策略
    @Bean
    public IRule ribbonRule(){
        return new RandomRule();
    }

    /**
     * 添加LoadBalanced，使RestTemplate已负载均衡的方式调用服务
     * @return
     */
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
