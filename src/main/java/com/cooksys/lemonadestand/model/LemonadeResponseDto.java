package com.cooksys.lemonadestand.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data									// DTOs exist in the Presentation Layer and abstract the user away from our Service/Persistence Layers
public class LemonadeResponseDto {  	// object we return to the user, including any fields we'd like them to see
	
	private Long id;					// note: we're naming the fields the same as in our lemonade entity
										// MapStruck cares about field names. We're auto-generating the getters & setters
	private int iceCubes;				// so when objects are being populated, the names are cross-referenced (Mapped between! ^_^)
										// and it'll confuse the poor robot brain if we name them diff things :)
	private double lemonJuice;
	private double water;
	private double sugar;
	
	private double price;
}
// when manually mapping between DTO and our returned object List, we overload constructors & add an AllArgs so we can map between

// purpose of DTO layer is to limit and control external clients sees, and what they are expected
// to give us when working with our API. client is abstracted away from our internal workings
// all the client needs to know, is what inputs to give & what output to expect back