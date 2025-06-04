//package yun.config;
//
//
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.loadbalancer.core.RandomLoadBalancer;
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
//
//import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
//
//import org.springframework.core.env.Environment;
//
//@Configuration
//public class CustomRandomLoadBalanceConfig {
//    @Bean
//    ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(Environment env, LoadBalancerClientFactory lbf) {
//        String name = env.getProperty(LoadBalancerClientFactory.PROPERTY_NAME);
//        return new RandomLoadBalancer(lbf.getLazyProvider(name, ServiceInstanceListSupplier.class), name);
//    }
//}