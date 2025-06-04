package yun.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import yun.client.UserClient;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private UserClient userClient; // 注入Feign客户端

    @GetMapping("/{orderId}")
    public String getOrder(@PathVariable("orderId") String orderId) {
        String userInfo = userClient.getUser("123"); // 直接调用接口
        return "Order: " + orderId + " | User: " + userInfo;
    }
}