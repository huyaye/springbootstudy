package com.example.api;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.domain.Customer;
import com.example.service.CustomerService;

@RestController
@RequestMapping("api/customers")
public class CustomRestController {
	@Autowired
	CustomerService customerService;

	@RequestMapping(method = RequestMethod.GET)
	List<Customer> getCustomers() {
		List<Customer> customers = customerService.findAll();
		return customers;
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	Customer getCustomer(@PathVariable Integer id) {
		Customer customer = customerService.findOne(id);
		return customer;
	}

	@RequestMapping(method = RequestMethod.POST)
	ResponseEntity<Customer> postCustomers(@RequestBody Customer customer, UriComponentsBuilder uriBuilder) {
		Customer created = customerService.create(customer);
		URI location = uriBuilder.path("api/customers/{id}").buildAndExpand(created.getId()).toUri();
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(location);
		return new ResponseEntity<>(created, headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.PUT)
	Customer putCustomers(@PathVariable Integer id, @RequestBody Customer customer) {
		customer.setId(id);
		return customerService.update(customer);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.DELETE)
	@ResponseStatus(HttpStatus.NO_CONTENT)
	void deleteCustomer(@PathVariable Integer id) {
		customerService.delete(id);
	}
}
