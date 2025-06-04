package yun.controller;

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
    public String getUser(@PathVariable("userId") String userId) {
        return userClient.getUser(userId);
    }

    // POST 调用示例（Feign）
    @PostMapping("/create")
    public String createUser() {
        Map<String, String> user = new HashMap<>();
        user.put("id", "1001");
        user.put("name", "John Doe");
        return userClient.createUser(user);
    }

    // PUT 调用示例（Feign）
    @PutMapping("/update/{userId}")
    public String updateUser(@PathVariable("userId") String userId) {
        Map<String, String> user = new HashMap<>();
        user.put("name", "Jane Doe");
        return userClient.updateUser(userId, user);
    }

    // DELETE 调用示例（Feign）
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        return userClient.deleteUser(userId);
    }
}