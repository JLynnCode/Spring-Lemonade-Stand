package com.cooksys.lemonadestand.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity   						// marks this class as an Entity :)
@NoArgsConstructor          	// generates our empty Constructor for Java Beans. part of Lombok (pkg to help reduce boiler plate)
//@Getter						// @Getters / @ Setters generates Getters & Setters for Java Beans. part of Lombok as well.
//@Setter						// placing the Get/Set above the class = all fields.  single fields = place above each desired field.
@Data     						// @Data will generate Gets/Sets, .equals(), .toString(), and .hashCode()
public class Customer {

	@Id   			  		// this is the primary key
	@GeneratedValue   		// auto generated value
	private Long id;
	
	@Column(nullable = false)   	    // sets notNull constraint to true; don't need @Column if not specifying constraints
	private String name;			    // primitives can't be null, wrapper types can. if we chose primitive, we don't ever want null
	
	@Column(nullable = false)
	private String phoneNumber;
									    // we included 'mappedBy = manyTable' on the OneToMany, with the value as its partner table
	@OneToMany(mappedBy = "customer")   // left(One) refers to this class, right(Many) refers to other class in the relationship
	private List<Order> orders;         // by default this will "Lazy Load" meaning it'll only query for them if we call their getter
									

}
