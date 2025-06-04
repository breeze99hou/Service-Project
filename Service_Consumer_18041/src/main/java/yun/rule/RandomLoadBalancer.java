//package yun.rule;
//
//
//import org.springframework.cloud.client.ServiceInstance;
//import org.springframework.cloud.client.loadbalancer.DefaultResponse;
//import org.springframework.cloud.client.loadbalancer.EmptyResponse;
//import org.springframework.cloud.client.loadbalancer.Request;
//import org.springframework.cloud.client.loadbalancer.Response;
//import org.springframework.cloud.loadbalancer.core.ReactorServiceInstanceLoadBalancer;
//import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
//import reactor.core.publisher.Mono;
//
//import java.util.List;
//import java.util.Random;
//
//public class RandomLoadBalancer implements ReactorServiceInstanceLoadBalancer {
//    private final ServiceInstanceListSupplier supplier;
//    private final String serviceId;
//    private final Random random = new Random();
//
//    public RandomLoadBalancer(ServiceInstanceListSupplier supplier, String serviceId) {
//        this.supplier = supplier;
//        this.serviceId = serviceId;
//    }
//
//    @Override
//    public Mono<Response<ServiceInstance>> choose(Request request) {
//        return supplier.get().next().map(this::getRandomInstanceResponse);
//    }
//
//    private Response<ServiceInstance> getRandomInstanceResponse(List<ServiceInstance> instances) {
//        if (instances.isEmpty()) {
//            return new EmptyResponse();
//        }
//        int index = random.nextInt(instances.size());
//        ServiceInstance serviceInstance = instances.get(index);
//        return new DefaultResponse(serviceInstance);
//    }
//}