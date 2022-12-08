package com.cooksys.lemonadestand.services;

import java.util.List;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;

public interface LemonadeService {
	
	Lemonade getLemonade(Long id);

	List<LemonadeResponseDto> getAllLemonades(); // gets all Lemonades from the DB and returns a list of Lemonade DTOs
	
	LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto);  // creates a new lemonade using the request DTO

	LemonadeResponseDto getLemonadeById(Long id);		// ResponseEntity<T> lets us control what status code to send, where T is the return type. Part of Spring

	LemonadeResponseDto updateLemonade(Long id, LemonadeRequestDto lemonadeRequestDto);

	LemonadeResponseDto deleteLemonade(Long id);
}


//===============OLD CODE EXAMPLE BELOW========================================================================================================

// -Handling Exceptions with ResponseEntity<T>

//public interface LemonadeService {
//
//	List<LemonadeResponseDto> getAllLemonades(); // gets all Lemonades from the DB and returns a list of Lemonade DTOs
//	
//	ResponseEntity<LemonadeResponseDto> createLemonade(LemonadeRequestDto lemonadeRequestDto);  // creates a new lemonade using the request DTO
//
//	ResponseEntity<LemonadeResponseDto> getLemonadeById(Long id);		// ResponseEntity<T> lets us control what status code to send, where T is the return type. Part of Spring
//}
