package com.feign.customer.service;

import org.springframework.stereotype.Service;

/**
 * TODO description
 * <p>
 *
 * @author booxj
 * @create 2019/6/20 14:58
 * @since
 */
@Service
public class FeignCustomerFallbackService implements FeignCustomerService {

    @Override
    public String hello(String name) {
        return "未找到" + name ;
    }
}
