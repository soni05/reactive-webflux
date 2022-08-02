package com.example.reactivewebflux.handler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterConfig {

    @Autowired
    private CustomerHandler customerHandler;

    @Autowired
    private  CustomerStreamHandler customerStreamHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction(){
        return RouterFunctions.route().GET("/router/customers", customerHandler::getCustomers)
                .GET("/router/customers/streams",customerHandler::getCustomers)
                .GET("router/customers/{input}", customerHandler::findCustomer)
                .POST("/router/customer/save", customerHandler::saveCustomer)
                .build();
    }
}
