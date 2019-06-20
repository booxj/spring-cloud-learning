package cloud.sleuth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 18:31
 * @since
 */
@RestController
public class ServiceBController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("b")
    public String index(){
        System.out.println("service b...");
        // 远程调用sleuth-service-c
        String result = remoteCallServiceC();
        return " service-b start ," + result;
    }

    // 远程调用sleuth-service-c
    private String remoteCallServiceC(){
        String url = "http://sleuth-service-c/c";
        String result = restTemplate.getForObject(url , String.class);
        return result;
    }
}
