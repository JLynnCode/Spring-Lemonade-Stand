package com.cooksys.lemonadestand.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class NotFoundException extends RuntimeException {
																		// RuntimeException is a serializable class, and since we are extending it, lets get that serial ID
	private static final long serialVersionUID = 6580296965767415034L;  // won't break if we don't add this, but best practice to add this for serialization

	private String message;
}
