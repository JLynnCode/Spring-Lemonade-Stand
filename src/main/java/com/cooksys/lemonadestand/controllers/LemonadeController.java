package com.cooksys.lemonadestand.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("lemonades")
@AllArgsConstructor
public class LemonadeController{
	
	private LemonadeService lemonadeService;
																		// server response DTO is sent to the controller & output to the client
	@GetMapping
	public List<LemonadeResponseDto> getAllLemonades(){
		return lemonadeService.getAllLemonades();						// the client req sends input values to the DTO
	}																	// the DTO is sent to the controller(here) where it is used to create a new object with the received values

	@GetMapping("/{id}")																
	public LemonadeResponseDto getLemonadeById(@PathVariable Long id){  // @PathVariable annotation marks the param as the value of the varArg in GetMapping
		return lemonadeService.getLemonadeById(id);										
	}	

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)  // specifying our own status code for successful creation
	public LemonadeResponseDto createLemonade(@RequestBody LemonadeRequestDto lemonadeRequestDto){
		return lemonadeService.createLemonade(lemonadeRequestDto);
	}													
	
	@PutMapping("/{id}")
	public LemonadeResponseDto updateLemonade(@PathVariable Long id, @RequestBody LemonadeRequestDto lemonadeRequestDto){
		
		return lemonadeService.updateLemonade(id, lemonadeRequestDto);
	}
	
	@DeleteMapping("/{id}")
	public LemonadeResponseDto deleteLemonade(@PathVariable Long id){
		
		return lemonadeService.deleteLemonade(id);
	}
}																						
																						

//===============OLD CODE EXAMPLE BELOW========================================================================================================


//@RestController									// tells Spring this handles HTTP requests following REST format (urls are nouns)
//@RequestMapping("lemonades")					// sets endpoint to /lemonades (handles everything in /lemonades)
//@AllArgsConstructor								//creates a constructor requiring all fields as params, thereby injecting them as dependencies
//public class LemonadeController {
//	
//	private LemonadeService lemonadeService;
//	
//	@GetMapping  									// annotation to map HTTP GET requests onto specific handler methods        						
//	public List<Lemonade> getAllLemonades(){		// GET requests don't have a body, just the specified endpoint above.
//													// so this will return all lemonades stored in /lemonades
//		return lemonadeService.getAllLemonades();	// passing on the request to the Service
//	}												// because a controller should never directly interact with a repository
//}													// user -> controller -> service -> repository -> database, then back again :)

// whatever our return type is in the controller, will be the body of the response
// so if our return type is null, the response won't have a body

//===============OLD CODE EXAMPLE BELOW========================================================================================================

// - Handling Exceptions with ResponseEntity<T>

//@RestController
//@RequestMapping("lemonades")
//@AllArgsConstructor
//public class LemonadeController{
//	
//	private LemonadeService lemonadeService;
//																		// server response DTO is sent to the controller & output to the client
//	@GetMapping
//	public List<LemonadeResponseDto> getAllLemonades(){
//		return lemonadeService.getAllLemonades();						// the client req sends input values to the DTO
//	}																	// the DTO is sent to the controller(here)
//																		// where it is used to create a new object with the received values
//	@PostMapping
//	public ResponseEntity<LemonadeResponseDto> createLemonade(@RequestBody LemonadeRequestDto lemonadeRequestDto){
//		return lemonadeService.createLemonade(lemonadeRequestDto);
//	}
//								
//	@GetMapping("/{id}")																// field name must match the path variable arg in GetMapping
//	public ResponseEntity<LemonadeResponseDto> getLemonadeById(@PathVariable Long id){  // @PathVariable annotation marks the param as the value of the varArg in GetMapping
//		return lemonadeService.getLemonadeById(id);										// could use @PathVariable(name="id") Long AnyNameWeWant to give the param a diff name
//	}																					// ResponseEntity<T> lets us control what status code to send, where T is the method return type
//}																						// ResponseEntity is part of Spring & represents the whole HTTP response: status code, header, body
																						// thus it allows us to fully config the HTTP response. have to return it from the endpoint!