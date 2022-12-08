package com.cooksys.lemonadestand.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity   						// marks this class as an Entity :)
@NoArgsConstructor          	// generates our empty Constructor for Java Beans. part of Lombok (pkg to help reduce boiler plate)
//Getter						// @Getters / @ Setters generates Getters & Setters for Java Beans. part of Lombok as well.
//Setter						// placing the Get/Set above the class = all fields.  single fields = place above each desired field.
@Data     						// @Data will generate Gets/Sets, .equals(), .toString(), and .hashCode()
public class Lemonade {

	@Id   						// this is the primary key
	@GeneratedValue   			// auto generated value
	private Long id;
	
	@Column(nullable = false)   // sets notNull constraint to true; don't need @Column if not specifying constraints
	private double lemonJuice;	// primitives can't be null, wrapper types can. if we chose primitive, we don't ever want null
	
	@Column(nullable = false)
	private double water;
	
	@Column(nullable = false)
	private double sugar;
	
	@Column(nullable = false)
	private int iceCubes;
	
	@Column(nullable = false)
	private double price;
	   							// since Order is on the One side of the relationship, we denote one Order object here.
	@ManyToOne					// left side (Many) relates to class we're in, right side (One) represents the other class in the relationship
	@JoinColumn					// creates a Join Column for the OtM / MtO rels, rather than a Join Table.
	private Order order;		// @JoinColumn goes with the ManyToOne fields
	
	//@Column
	private boolean deleted;    // delete flag to toggle on/off instead of completely removing an item from a database
	
}
