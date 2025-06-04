package yun.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("user-service")
public interface UserClient {

    @GetMapping("/users/{userId}")
    String getUser(@PathVariable("userId") String userId); // 明确指定路径变量名
}