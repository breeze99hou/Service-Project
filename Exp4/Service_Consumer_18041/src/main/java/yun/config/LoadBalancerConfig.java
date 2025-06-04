//package yun.config;
//
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.*;
//import org.springframework.cloud.loadbalancer.core.ReactorLoadBalancer;
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
//import org.springframework.cloud.loadbalancer.support.LoadBalancerClientFactory;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import reactor.core.publisher.Mono;
//import java.util.List;
//import java.util.Random;
//
//@Configuration
//public class LoadBalancerConfig {
//
//    @Bean
//    public ReactorLoadBalancer<ServiceInstance> randomLoadBalancer(
//            LoadBalancerClientFactory loadBalancerClientFactory
//    ) {
//        String serviceId = "user-service"; // 硬编码服务名
//
//        ServiceInstanceListSupplier supplier = loadBalancerClientFactory
//                .getLazyProvider(serviceId, ServiceInstanceListSupplier.class)
//                .getIfAvailable(() -> ServiceInstanceListSupplier.builder().build());
//
//        return new RandomLoadBalancer(supplier, serviceId);
//    }
//
//    static class RandomLoadBalancer implements ReactorLoadBalancer<ServiceInstance> {
//        private final ServiceInstanceListSupplier supplier;
//        private final String serviceId;
//        private final Random random = new Random();
//
//        public RandomLoadBalancer(ServiceInstanceListSupplier supplier, String serviceId) {
//            this.supplier = supplier;
//            this.serviceId = serviceId;
//        }
//
//        @Override
//        public Mono<Response<ServiceInstance>> choose(Request request) {
//            return supplier.get()
//                    .collectList()
//                    .map(instances -> {
//                        if (instances.isEmpty()) {
//                            return new EmptyResponse();
//                        }
//                        int index = random.nextInt(instances.size());
//                        return new DefaultResponse(instances.get(index));
//                    });
//        }
//    }
//}