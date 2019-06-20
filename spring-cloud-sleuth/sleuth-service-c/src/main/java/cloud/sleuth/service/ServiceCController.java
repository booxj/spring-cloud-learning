package cloud.sleuth.service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 18:33
 * @since
 */
@RestController
public class ServiceCController {

    @RequestMapping("/c")
    public String index(){
        System.out.println("service c...");
        return " service-c hello , end";
    }
}
