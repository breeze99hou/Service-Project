package yun.controller;

import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import yun.client.UserClient;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private UserClient userClient; // 注入 Feign 客户端

    // GET 调用示例（Feign）
    @GetMapping("/user/{userId}")
    @Bulkhead(name = "bulkheadA", fallbackMethod = "getUserFallback")
//    @CircuitBreaker(name = "circuitBreakerA", fallbackMethod = "getUserFallback")
    public String getUser(@PathVariable("userId") String userId) {
        return userClient.getUser(userId);
    }

    // POST 调用示例（Feign）
    @PostMapping("/create")
    @CircuitBreaker(name = "circuitBreakerB", fallbackMethod = "createUserFallback")
    public String createUser() {
        Map<String, String> user = new HashMap<>();
        user.put("id", "1001");
        user.put("name", "John Doe");
        return userClient.createUser(user);
    }

    // PUT 调用示例（Feign）
    @PutMapping("/update/{userId}")
    @CircuitBreaker(name = "circuitBreakerA", fallbackMethod = "updateUserFallback")
    public String updateUser(@PathVariable("userId") String userId) {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Jane Doe");
        return userClient.updateUser(userId, user);
    }

    // DELETE 调用示例（Feign）
    @DeleteMapping("/delete/{userId}")
    @CircuitBreaker(name = "circuitBreakerB", fallbackMethod = "deleteUserFallback")
    public String deleteUser(@PathVariable("userId") String userId) {
        return userClient.deleteUser(userId);
    }

    // 降级方法
     public String getUserFallback(String userId, Exception e) {
        return "用户查询服务暂时不可用，请稍后再试 (Fallback for getUser)";
    }

    public String createUserFallback(Map<String, String> user, Exception e) {
        return "用户创建服务暂时不可用，请稍后再试 (Fallback for createUser)";
    }

    public String updateUserFallback(String userId, Map<String, String> user, Exception e) {
        return "用户更新服务暂时不可用，请稍后再试 (Fallback for updateUser)";
    }

    public String deleteUserFallback(String userId, Exception e) {
        return "用户删除服务暂时不可用，请稍后再试 (Fallback for deleteUser)";
    }
}