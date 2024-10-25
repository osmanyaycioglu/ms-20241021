package org.training.microservice.msorder;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.core.ServiceInstanceListSupplier;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.training.microservice.mscommon.error.ErrorConfig;

@SpringBootApplication
@EnableFeignClients
@EnableDiscoveryClient
@Import(ErrorConfig.class)
public class MsOrderApplication {

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }


//    @Bean
//    public ServiceInstanceListSupplier discoveryClientSupplier(ConfigurableApplicationContext contextParam) {
//        return ServiceInstanceListSupplier.builder()
//                                          .withBlockingDiscoveryClient()
//                                          .withBlockingHealthChecks()
//                                          .withCaching()
//                                          .build(contextParam);
//    }

    public static void main(String[] args) {
        SpringApplication.run(MsOrderApplication.class,
                              args);
    }
}
