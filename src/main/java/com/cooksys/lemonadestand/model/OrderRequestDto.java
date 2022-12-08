package com.cooksys.lemonadestand.model;

import java.util.List;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class OrderRequestDto {
	
	private List<LemonadeDto> lemonade;
	
	private CustomerDto customer;
	
	private LemonadeStandDto lemonadeStand;
}
