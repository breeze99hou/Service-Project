package yun.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/users")
public class UserController {
    // 模拟内存数据库
    private final Map<String, String> userDatabase = new HashMap<>();
    public UserController() {
        userDatabase.put("1", "John");
    }
    // GET 获取用户信息
    @GetMapping("/{userId}")
    public String getUser(@PathVariable("userId") String userId) {
        System.out.println("from 18082,getUser:"+userId);
        return userDatabase.getOrDefault(userId, "from 18082,User not found: " + userId);
    }

    // POST 创建用户
    @PostMapping
    public String createUser(@RequestBody Map<String, String> user) {
        String userId = user.get("id");
        String userName = user.get("name");
        userDatabase.put(userId, "Name: " + userName);
        return "User created: " + userId;
    }

    // PUT 更新用户信息
    @PutMapping("/{userId}")
    public String updateUser(@PathVariable("userId") String userId,
                             @RequestBody Map<String, String> user) {
        if (!userDatabase.containsKey(userId)) {
            return "User not found: " + userId;
        }
        String userName = user.get("name");
        userDatabase.put(userId, "Name: " + userName);
        return "User updated: " + userId;
    }

    // DELETE 删除用户
    @DeleteMapping("/{userId}")
    public String deleteUser(@PathVariable("userId") String userId) {
        if (!userDatabase.containsKey(userId)) {
            return "User not found: " + userId;
        }
        userDatabase.remove(userId);
        return "User deleted: " + userId;
    }
}