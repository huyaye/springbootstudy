package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import com.example.domain.Customer;
import com.example.repository.CustomerRepository;

@EnableAutoConfiguration
@ComponentScan
public class App implements CommandLineRunner {
	@Autowired
	CustomerRepository customerRepository;

	@Override
	public void run(String... strings) throws Exception {
		Customer created = customerRepository.save(new Customer(null, "Hidetoshi", "Dekisugi"));
		System.out.println(created + " is created!");

		customerRepository.findAllOrderByName().forEach(System.out::println);

		// Paging
		Pageable pageable = new PageRequest(1, 3);
		Page<Customer> page = customerRepository.findAll(pageable);
		System.out.println("한 페이지당 데이터 수=" + page.getSize());
		System.out.println("현재 페이지=" + page.getNumber());
		System.out.println("전체 페이지 수=" + page.getTotalPages());
		System.out.println("전체 데이터 수=" + page.getTotalElements());
		page.getContent().forEach(System.out::println);
	}

	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
	}
}
