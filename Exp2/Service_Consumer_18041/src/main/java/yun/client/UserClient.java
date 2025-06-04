package yun.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;
import yun.config.CustomLoadBalancerConfig;
//import yun.config.CustomRandomLoadBalanceConfig;


import java.util.Map;

@FeignClient(name = "user-service",
            configuration = CustomLoadBalancerConfig.class
            )

public interface UserClient {
    // GET 方法
    @GetMapping("/users/{userId}")
    String getUser(@PathVariable("userId") String userId);

    // POST 方法
    @PostMapping("/users")
    String createUser(@RequestBody Map<String, String> user);

    // PUT 方法
    @PutMapping("/users/{userId}")
    String updateUser(@PathVariable("userId") String userId, @RequestBody Map<String, String> user);

    // DELETE 方法
    @DeleteMapping("/users/{userId}")
    String deleteUser(@PathVariable("userId") String userId);
}