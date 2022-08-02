package com.example.reactivewebflux.dao;

import com.example.reactivewebflux.dto.Customer;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

// publisher 

@Component
public class CustomerDao {

    public List<Customer> getAllCustomers() throws InterruptedException {
        return IntStream.rangeClosed(1,50)
                .peek(CustomerDao::sleepExecution)
                .peek(i-> System.out.println("processing count : "  +  i))
                .mapToObj(i-> new Customer(i, "cusotmer"+i)).collect(Collectors.toList());
    }


    public Flux<Customer> getAllCustomersStream()  {
        return Flux.range(1,50)//.delayElements(Duration.ofSeconds(1))
                .doOnNext(i-> System.out.println("Processing " + i))
                .map(i-> new Customer(i, "cusotmer"+i));
    }

    private static void sleepExecution(int i) {
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){

        }

    }

    public Mono<Customer> findCustomer(int customerId) {
        return getAllCustomersStream().filter(customer-> customer.getId() == customerId).next();

    }
}
