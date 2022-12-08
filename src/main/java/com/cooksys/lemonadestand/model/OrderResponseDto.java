package com.cooksys.lemonadestand.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderResponseDto {
	
	private Long id;
	private double total;
	private List<LemonadeResponseDto> lemonades;
	private CustomerResponseDto customer;
	private LemonadeStandDto lemonadeStand;
}
