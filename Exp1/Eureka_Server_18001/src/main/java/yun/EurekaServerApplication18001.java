package yun;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;


@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication18001 {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication18001.class, args);
    }

}
