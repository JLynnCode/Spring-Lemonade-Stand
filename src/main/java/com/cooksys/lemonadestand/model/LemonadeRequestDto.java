package com.cooksys.lemonadestand.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data									// DTOs exist in the Presentation Layer and abstract the user away from our Service/Persistence Layers
public class LemonadeRequestDto {  		// Object created on user request, including any fields we want the user to set values for

	private Double lemonJuice;
	private Double water;
	private Double sugar;
	private Integer iceCubes;			// changed to Wrapper types so we could do null check & set status code
	
	// purpose of DTO layer is to limit and control external clients sees, and what they are expected
	// to give us when working with our API. client is abstracted away from our internal workings
	// all the client needs to know, is what inputs to give & what output to expect back
}
