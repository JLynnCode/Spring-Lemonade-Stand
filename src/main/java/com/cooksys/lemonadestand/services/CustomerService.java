package com.cooksys.lemonadestand.services;

import java.util.List;

import com.cooksys.lemonadestand.model.CustomerRequestDto;
import com.cooksys.lemonadestand.model.CustomerResponseDto;

public interface CustomerService {

	CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto);
	
	List<CustomerResponseDto> getAllCustomers();

	CustomerResponseDto getCustomerById(Long id);

	CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto);

	CustomerResponseDto deleteCustomer(Long id);
}
