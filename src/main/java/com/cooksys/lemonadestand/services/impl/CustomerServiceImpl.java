package com.cooksys.lemonadestand.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Customer;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.CustomerMapper;
import com.cooksys.lemonadestand.model.CustomerRequestDto;
import com.cooksys.lemonadestand.model.CustomerResponseDto;
import com.cooksys.lemonadestand.repositories.CustomerRepository;
import com.cooksys.lemonadestand.services.CustomerService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	
	private final CustomerRepository customerRepository;
	private final CustomerMapper customerMapper;
			
//============================================================================================================================================================		
		
	private void validateCustomerRequest(CustomerRequestDto customerRequestDto){
		
		if(customerRequestDto.getName() == null || customerRequestDto.getPhoneNumber() == null){
					
			throw new BadRequestException("All fields are required on a customer request DTO.");     
		}
	}

//============================================================================================================================================================	

	private Customer getCustomer(Long id){
			
		Optional<Customer> optionalCustomer = customerRepository.findById(id);
			
		if(optionalCustomer.isEmpty()){
			throw new NotFoundException("No customer found with id " + id + ". Please verify the id number and try again.");
		}
			
		return optionalCustomer.get();
	}	
	
//============================================================================================================================================================		
	
	@Override
	public CustomerResponseDto createCustomer(CustomerRequestDto customerRequestDto) {
		
		validateCustomerRequest(customerRequestDto);
				
		return customerMapper.entityToResponseDto(customerRepository.saveAndFlush
			  (customerMapper.requestDtoToEntity(customerRequestDto)));
	}

//============================================================================================================================================================	
	
	@Override
	public List<CustomerResponseDto> getAllCustomers(){
		
		return customerMapper.entitiesToResponseDtos(customerRepository.findAll());
	}

//============================================================================================================================================================	
	
	@Override
	public CustomerResponseDto getCustomerById(Long id) {
		
		return customerMapper.entityToResponseDto(getCustomer(id));
	}

//============================================================================================================================================================		
	
	@Override
	public CustomerResponseDto updateCustomer(Long id, CustomerRequestDto customerRequestDto) {
		//using PATCH to update here, hence why there's no validation check & we have conditions to update single fields
		Customer customerToUpdate = getCustomer(id);
		
		if(customerRequestDto.getName() != null){
			customerToUpdate.setName(customerRequestDto.getName());
		}
		if(customerRequestDto.getPhoneNumber() != null){
			
			customerToUpdate.setPhoneNumber(customerRequestDto.getPhoneNumber());
		}
		
		return customerMapper.entityToResponseDto(customerRepository.saveAndFlush(customerToUpdate));
	}

//============================================================================================================================================================	
	
	@Override
	public CustomerResponseDto deleteCustomer(Long id) {
		
		Customer customerToDelete = getCustomer(id);
		
		customerRepository.delete(customerToDelete);
		
		return customerMapper.entityToResponseDto(customerToDelete);
	}
}
