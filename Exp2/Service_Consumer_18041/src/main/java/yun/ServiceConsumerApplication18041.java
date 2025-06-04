package yun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;
import yun.config.CustomLoadBalancerConfig;
//import yun.config.CustomRandomLoadBalanceConfig;

@SpringBootApplication
@EnableFeignClients
@LoadBalancerClient(name = "user-service", configuration = CustomLoadBalancerConfig.class)
public class ServiceConsumerApplication18041 {

    @Bean

    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumerApplication18041.class, args);
    }

}
