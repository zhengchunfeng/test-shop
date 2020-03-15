package com.test.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author zhengchunfeng
 * @version 1.0
 * @className TestClientFeign
 * @description
 * @date 2020/3/15 14:19
 */
@FeignClient(name = "test-client")
public interface TestClientFeign {


    @GetMapping("/rest/test")
    String test();
}
