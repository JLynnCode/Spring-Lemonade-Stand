package com.cooksys.lemonadestand.entities;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "order_table")  // renaming our table in the DB, but keeping class name same in Java. "order" is a reserved SQL keyword
@Entity   						// marks this class as an Entity :)
@NoArgsConstructor          	// generates our empty Constructor for Java Beans. part of Lombok (pkg to help reduce boiler plate)
//@Getter						// @Getters / @ Setters generates Getters & Setters for Java Beans. part of Lombok as well.
//@Setter						// placing the Get/Set above the class = all fields.  single fields = place above each desired field.
@Data     						// @Data will generate Gets/Sets, .equals(), .toString(), and .hashCode()
public class Order {
	
	@Id   					// this is the primary key
	@GeneratedValue   		// auto generated value
	private Long id;
	
	@Column(nullable = false)   		// sets notNull constraint to true; don't need @Column if not specifying constraints
	private double total;				// primitives can't be null, wrapper types can. if we chose primitive, we don't ever want null
	
	@OneToMany(mappedBy = "order")	// left(One) refers to this class, right(Many) refers to other class in the relationship
	private List<Lemonade> lemonades;   // by default this will "Lazy Load" meaning it'll only query for them if we call their getter
										// we included 'mappedBy = manyTable' on the OneToMany, with the value as its partner table
	@ManyToOne
	@JoinColumn							// creates a Join Column for the OtM / MtO rels, rather than a Join Table.
	private Customer customer;			// only need a Join Table for the MtM rels

	@ManyToOne
	@JoinColumn							// we add the JoinColumn notation over the "ManyToOne" fields
	private LemonadeStand lemonadeStand;
}
