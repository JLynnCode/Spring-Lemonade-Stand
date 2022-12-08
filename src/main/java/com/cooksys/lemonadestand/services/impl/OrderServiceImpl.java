package com.cooksys.lemonadestand.services.impl;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.entities.Order;
import com.cooksys.lemonadestand.mappers.OrderMapper;
import com.cooksys.lemonadestand.model.OrderRequestDto;
import com.cooksys.lemonadestand.model.OrderResponseDto;
import com.cooksys.lemonadestand.repositories.OrderRepository;
import com.cooksys.lemonadestand.services.LemonadeService;
import com.cooksys.lemonadestand.services.OrderService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
	
	private final OrderRepository orderRepository;
	private final LemonadeService lemonadeService;  // note: if two services rely on each other = infinite loop. it's ok for only one to rely on another though.

	private final OrderMapper orderMapper;
	
	private void setOrderTotal(Order order){
		
		double total = 0.0;
		
		for(Lemonade lemonade : order.getLemonades()){
			
			lemonade = lemonadeService.getLemonade(lemonade.getId());  // we made getLemonade(id) public, and added the method to LemonadeService
			total += lemonade.getPrice();
		}
		order.setTotal(total);
	}
	
	@Override
	public OrderResponseDto createOrder(OrderRequestDto orderRequestDto) {
		
		Order order = orderMapper.requestDtoToEntity(orderRequestDto);
		setOrderTotal(order);
		return orderMapper.entityToResponseDto(orderRepository.saveAndFlush(order));
	}
}
