package com.cooksys.lemonadestand;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.cooksys.lemonadestand.entities.Customer;
import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.entities.Order;
import com.cooksys.lemonadestand.repositories.CustomerRepository;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.repositories.LemonadeStandRepository;
import com.cooksys.lemonadestand.repositories.OrderRepository;

import lombok.AllArgsConstructor;

@Component						// tells Spring this is a component class & to manage it, Component also has more specific child classes
@AllArgsConstructor				// generates our constructor with all fields as args, part of Lombok.
public class DatabaseSeeder implements CommandLineRunner {
													 	// Command Line Runner will run before the web app starts, finishing set up
													 	// Java App started -> Servlet started -> Command Line Runner -> Web Service
	
    private LemonadeRepository lemonadeRepository;   	// dependencies being injected, as its a req arg for the constructor generated
    private LemonadeStandRepository lemonadeStandRepository;
    private CustomerRepository customerRepository;
    private OrderRepository orderRepository;
    													// this method will run just before our Web App starts, populating the DB
	@Override											// only time we'll use the new keyword with Spring
	public void run(String... args) throws Exception {

		Lemonade lemonade = new Lemonade();
		lemonade.setLemonJuice(3.25);
		lemonade.setWater(2.5);
		lemonade.setSugar(1.25);
		lemonade.setIceCubes(5);
		lemonade.setPrice(4.50);
		
		LemonadeStand lemonadeStand = new LemonadeStand();
		lemonadeStand.setName("The Citrus Citadel of Morgoth");
		
		Customer customer = new Customer();
		customer.setName("Jeremy");
		customer.setPhoneNumber("0100010110");
		
		Order order = new Order();
		order.setTotal(4.50);
		
		lemonadeRepository.saveAndFlush(lemonade);
		lemonadeStandRepository.saveAndFlush(lemonadeStand);
		customerRepository.saveAndFlush(customer);
		orderRepository.saveAndFlush(order);
	}
}
/*
We're using Lombok to generate the constructor, but here's the boiler plate example :)
Database Seeder creation is dependent on having a LemonadeRepository
So it has to create one first before creating a Database Seeder
this is dependency injection :)
   
public DatabaseSeeder(LemonadeRepository lemonadeRepository){
this.lemonadeRepository = lemonadeRepository;
}
*/