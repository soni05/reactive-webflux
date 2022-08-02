package com.example.reactivewebflux;

import com.example.reactivewebflux.dao.CustomerDao;
import com.example.reactivewebflux.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
public class CustomerService {

    @Autowired
    private CustomerDao dao;


    public List<Customer> loadAllCustomers() throws InterruptedException {
        long start = System.currentTimeMillis();
        List<Customer> customers = dao.getAllCustomers();
        long end  = System.currentTimeMillis();
        System.out.println("Total execution time :" + (end - start));
        return customers;
    }

    public Flux<Customer> loadAllCustomersStream() throws InterruptedException {
        long start = System.currentTimeMillis();
        Flux<Customer> customers = dao.getAllCustomersStream();
        long end  = System.currentTimeMillis();
        System.out.println("Total execution time :" + (end - start));
        return customers;
    }

}
