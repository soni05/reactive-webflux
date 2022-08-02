package com.example.reactivewebflux.handler;


import com.example.reactivewebflux.dao.CustomerDao;
import com.example.reactivewebflux.dto.Customer;
import com.sun.deploy.xml.CustomParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerHandler {

    @Autowired
    private CustomerDao customerDao;

    public Mono<ServerResponse> getCustomers(ServerRequest serverRequest){
        Flux<Customer> customerFlux = customerDao.getAllCustomersStream();
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }

    public Mono<ServerResponse> findCustomer( ServerRequest serverRequest){
        int customerId = Integer.valueOf(serverRequest.pathVariable("input"));
        Mono<Customer> customerFlux = customerDao.findCustomer(customerId);
        return ServerResponse.ok().body(customerFlux, Customer.class);
    }


    public Mono<ServerResponse> saveCustomer(ServerRequest serverRequest) {
        Mono<Customer> customerMono = serverRequest.bodyToMono(Customer.class);
        return ServerResponse.ok().body(customerMono.map(dto -> dto.getId() + ": " + dto.getName()), String.class);
    }
}
