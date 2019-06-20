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
public class ServiceAController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping("a")
    public String index(){
        // 远程调用sleuth-service-b
        String result = remoteCallServiceB();
        return " service-a start ," + result;
    }

    // 远程调用sleuth-service-b
    private String remoteCallServiceB(){
        String url = "http://sleuth-service-b/b";
        String result = restTemplate.getForObject(url , String.class);
        return result;
    }
}
