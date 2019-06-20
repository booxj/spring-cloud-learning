package com.feign.customer.web;

import com.feign.customer.service.FeignCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:56
 * @since
 */
@RestController
public class FeignCustomerController {

    @Autowired
    private FeignCustomerService feignCustomerService;

    @RequestMapping(value = "/feign/customer/hello" , method = RequestMethod.GET)
    public String hello(String name){
        return feignCustomerService.hello(name);
    }

}
