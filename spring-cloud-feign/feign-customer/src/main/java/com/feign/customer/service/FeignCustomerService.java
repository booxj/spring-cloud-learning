package com.feign.customer.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:56
 * @since
 */
@FeignClient(name = "feign-provider",fallback = FeignCustomerFallbackService.class)
public interface FeignCustomerService {

    @RequestMapping(value = "/feign/provider/hello" , method = RequestMethod.GET)
    String hello(@RequestParam("name") String name);
}
