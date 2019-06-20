package cloud.ribbon.customer.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:20
 * @since
 */
@RestController
public class RibbonCustomerController {

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ribbon/customer/hello")
    public String sayHello(String name){
        String url = "http://ribbon-provider/ribbon/provider/hello?name=" + name;
        String result = restTemplate.getForObject(url , String.class);
        return result;
    }
}
