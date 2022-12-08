package com.cooksys.lemonadestand.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.lemonadestand.entities.Lemonade;

@Repository				// marks this as a class that interacts with a DB, child of component class which Spring instantiates & maintains for us
public interface LemonadeRepository extends JpaRepository<Lemonade, Long>{
// extends JpaRepository<EntityClass, IDTypeForEntityClass> 
// extending this interface provides us with methods to store/retrieve data on our DB
// interfaces extending JpaRepository are acting as our DAOs for serializing/deserializing entities to/from our DB
// e.g. converts between Lemonade Entities and DB tables
	
// Spring is going to generate an implementation for its methods based on the driver we tell it to use
// so we're "decoupled" from the DB & this is what keeps us flexible to switch between DB types
	
	Optional<Lemonade> findByIdAndDeletedFalse(Long id); 
	// derived queries ^^^ vvv
	List<Lemonade> findAllByDeletedFalse();
}

// the Optional replaces null; finds a lemonade by id & finds it inside an Optional object, and the Optional object provides wrappers so
// we never get null. Optional provides methods such as isPresent or get a lemonade out of it etc
// Optional, it might be there it might not weewoo