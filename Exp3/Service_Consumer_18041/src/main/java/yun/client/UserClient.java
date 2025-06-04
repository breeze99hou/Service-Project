package yun.client;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
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
//    @CircuitBreaker(name = "circuitBreakerA", fallbackMethod = "getUserFallback")
    @Bulkhead(name = "bulkheadA", fallbackMethod = "getUserFallback")
//    @RateLimiter(name = "rateLimiterA", fallbackMethod = "getUserFallback")
    String getUser(@PathVariable("userId") String userId);

    // POST 方法
    @CircuitBreaker(name = "circuitBreakerB", fallbackMethod = "createUserFallback")
    @PostMapping("/users")
    String createUser(@RequestBody Map<String, String> user);

    // PUT 方法
    @CircuitBreaker(name = "circuitBreakerA", fallbackMethod = "updateUserFallback")
    @PutMapping("/users/{userId}")
    String updateUser(@PathVariable("userId") String userId, @RequestBody Map<String, String> user);

    // DELETE 方法
    @CircuitBreaker(name = "circuitBreakerB", fallbackMethod = "deleteUserFallback")
    @DeleteMapping("/users/{userId}")
    String deleteUser(@PathVariable("userId") String userId);
    // 降级方法
    default String getUserFallback(String userId, Exception e) {
        System.out.println("用户服务暂时不可用，请稍后再试 (Fallback for getUser)" );
        return "用户服务暂时不可用，请稍后再试 (Fallback for getUser)";
    }

    default String createUserFallback(Map<String, String> user, Exception e) {
        return "用户创建服务暂时不可用，请稍后再试 (Fallback for createUser)";
    }

    default String updateUserFallback(String userId, Map<String, String> user, Exception e) {
        return "用户更新服务暂时不可用，请稍后再试 (Fallback for updateUser)";
    }

    default String deleteUserFallback(String userId, Exception e) {
        return "用户删除服务暂时不可用，请稍后再试 (Fallback for deleteUser)";
    }
}