package yun.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FallbackController {
    @GetMapping("/fallback")
    public String userFallback() {
        return "服务暂时不可用，请稍后重试 (网关熔断保护)";
    }
}
