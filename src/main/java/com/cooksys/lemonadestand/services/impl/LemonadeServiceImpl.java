package com.cooksys.lemonadestand.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.LemonadeMapper;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeRepository;
import com.cooksys.lemonadestand.services.LemonadeService;

import lombok.AllArgsConstructor;

@Service																	// child of Component annotation. 
@AllArgsConstructor															//creates a constructor requiring all fields as params, thereby injecting them as dependencies
public class LemonadeServiceImpl implements LemonadeService {
	
	private LemonadeRepository lemonadeRepository;
	private LemonadeMapper lemonadeMapper;


	
//============================================================================================================================================================		
	
	private void setLemonadePrice(Lemonade lemonade){
		
		lemonade.setPrice(lemonade.getLemonJuice() * .20 + lemonade.getWater() * 0.1 +
				lemonade.getSugar() * .15 + lemonade.getIceCubes() * .05 + .50);
	}
	
//============================================================================================================================================================		
	
	private void validateLemonadeRequest(LemonadeRequestDto lemonadeRequestDto){
		if(lemonadeRequestDto.getLemonJuice() == null || lemonadeRequestDto.getWater() == null
		   || lemonadeRequestDto.getSugar() == null || lemonadeRequestDto.getIceCubes() == null){
					
			throw new BadRequestException("All fields are required on a lemonade request DTO.");     
		}   // throwing our custom exception, message arg
	}

//============================================================================================================================================================	
	
	@Override
	public Lemonade getLemonade(Long id){
		// Better practice to use Optional in place of basic null checks
		// Storing a lemonade with a specific id in an Optional object
		// Derived Query .findById() is in the Repo. we did this because getRefById will throw its own exception
		
		Optional<Lemonade> optionalLemonade = lemonadeRepository.findByIdAndDeletedFalse(id);
		
		// Optional.isEmpty() to check if it is null	
		if(optionalLemonade.isEmpty()){
			throw new NotFoundException("No lemonade found with id " + id + ". Please verify the id number and try again.");
		}
		
		// Optional.get() to pull the lemonade object out of the Optional object
		return optionalLemonade.get();
	}
//============================================================================================================================================================	
	
	@Override
	public List<LemonadeResponseDto> getAllLemonades() {
		// all 3 mapping steps listed below, but in one line ~woah~
		return lemonadeMapper.entitiesToResponseDto(lemonadeRepository.findAllByDeletedFalse());
	}
	
//============================================================================================================================================================		
	
	@Override
	public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto){
		
		validateLemonadeRequest(lemonadeRequestDto);
		
		// Map the RequestDTO to a Lemonade Entity
		Lemonade lemonadeToSave = lemonadeMapper.requestDtoToEntity(lemonadeRequestDto);
		
		setLemonadePrice(lemonadeToSave);
		
		lemonadeToSave.setDeleted(false);
		
		// Save the new Lemonade Entity
		// Map newly saved Entity, saved with a generated ID, to a ResponseDTO and return it
		return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToSave));
	}

//============================================================================================================================================================	
	
	@Override																						
	public LemonadeResponseDto getLemonadeById(Long id) {		

		return lemonadeMapper.entityToResponseDto(getLemonade(id));
	}
	
//============================================================================================================================================================	
	
	@Override
	public LemonadeResponseDto updateLemonade(Long id, LemonadeRequestDto lemonadeRequestDto){
		
		validateLemonadeRequest(lemonadeRequestDto);
				
		Lemonade lemonadeToUpdate = getLemonade(id);
		lemonadeToUpdate.setIceCubes(lemonadeRequestDto.getIceCubes());
		lemonadeToUpdate.setLemonJuice(lemonadeRequestDto.getLemonJuice());
		lemonadeToUpdate.setWater(lemonadeRequestDto.getWater());
		lemonadeToUpdate.setSugar(lemonadeRequestDto.getSugar());
		
		setLemonadePrice(lemonadeToUpdate);
		
		return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToUpdate));
	}

//============================================================================================================================================================	
		
	public LemonadeResponseDto deleteLemonade(Long id){					// Note: This is not best practice. Usually, we won't ever fully delete items from
																	    // an enterprise database, for the sake of audits & record keeping etc.
		Lemonade lemonadeToDelete = getLemonade(id);					// Instead, we'd add a "deleted" flag to the Entity itself. And for the Delete method
																		// it would just toggle that flag (boolean). Then if it's flag is set to true, we won't
		//lemonadeRepository.delete(lemonadeToDelete);					// be able to perform our normal CRUD ops on it.
		lemonadeToDelete.setDeleted(true);
		//return lemonadeMapper.entityToResponseDto(lemonadeToDelete);
		return lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToDelete));
	}
}
//===============OLD CODE EXAMPLE BELOW=======================================================================================================================	

// this is where we implement the methods in our LemonadeService controller interface
// Lemonade Controller is dependent on a LemonadeService, and calls methods on it
// and the methods in LemonadeService interface are available to whatever class implements it
// we can change the implementation in LemonadeServiceImpl without needing to change anything
// in our Controllers! :) layering & stuff bro, onions, modular

/* manually mapping our lemonades to response DTOs. also added an overloaded AllArgsConstructor to the ResponseDto
  
	@Override
	public List<LemonadeResponseDto> getAllLemonades() {
		
		// Create a new ArrayList of LemonadeResponseDto
		List<LemonadeResponseDto> result = new ArrayList<LemonadeResponseDto>();
		
		// Mapping the Lemonade Entities to LemonadeResponseDtos and storing them in our ArrayList
		for(Lemonade lemonade : lemonadeRepository.findAll()){
			result.add(new LemonadeResponseDto(lemonade.getId(), lemonade.getPrice()));
		}
		
		// Returning our newly-created ArrayList containing all of our LemonadeResponseDtos
		return result;
	}
	
manually mapping our request DTOs to new Lemonade objects

	@Override
	public LemonadeResponseDto createLemonade(LemonadeRequestDto lemonadeRequestDto){
		
	// Map the RequestDTO to a Lemonade Entity
		Lemonade lemonadeToSave = new Lemonade();
		lemonadeToSave.setLemonJuice(lemonadeRequestDto.getLemonJuice());
		lemonadeToSave.setWater(lemonadeRequestDto.getWater());
		lemonadeToSave.setSugar(lemonadeRequestDto.getSugar());
		lemonadeToSave.setIceCubes(lemonadeRequestDto.getIceCubes());
		lemonadeToSave.setPrice(lemonadeToSave.getLemonJuice() * .20 + lemonadeToSave.getWater() * 0.1 +
								lemonadeToSave.getSugar() * .15 + lemonadeToSave.getIceCubes() * .05 + .50);
		
	// Save the new Lemonade Entity and store the resulting entity with the ID generated from the database in a variable named savedLemonade
		Lemonade savedLemonade = lemonadeRepository.saveAndFlush(lemonadeToSave);
		
	// Map the newly saved entity with the generated ID to a response DTO and return it
		return new LemonadeResponseDto(savedLemonade.getId(), savedLemonade.getPrice());
	}
 * */

//===============OLD CODE EXAMPLE BELOW=======================================================================================================================	

// -Handling Exceptions with ResponseEntity<T>

//@Service							// child of Component annotation. 
//@AllArgsConstructor					//creates a constructor requiring all fields as params, thereby injecting them as dependencies
//public class LemonadeServiceImpl implements LemonadeService {
//	
//	private LemonadeRepository lemonadeRepository;
//	private LemonadeMapper lemonadeMapper;
//	
//	@Override
//	public List<LemonadeResponseDto> getAllLemonades() {
//		// all 3 mapping steps listed below, but in one line ~woah~
//		return lemonadeMapper.entitiesToResponseDto(lemonadeRepository.findAll());
//	}
//	
//	@Override
//	public ResponseEntity<LemonadeResponseDto> createLemonade(LemonadeRequestDto lemonadeRequestDto){
//		
//		if(lemonadeRequestDto.getLemonJuice() == null || lemonadeRequestDto.getWater() == null
//		   || lemonadeRequestDto.getSugar() == null | lemonadeRequestDto.getIceCubes() == null){
//			
//			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
//		}
//		// Map the RequestDTO to a Lemonade Entity
//		Lemonade lemonadeToSave = lemonadeMapper.requestDtoToEntity(lemonadeRequestDto);
//		
//		// calculating price, specific to this example project
//		lemonadeToSave.setPrice(lemonadeToSave.getLemonJuice() * .20 + lemonadeToSave.getWater() * 0.1 +
//								lemonadeToSave.getSugar() * .15 + lemonadeToSave.getIceCubes() * .05 + .50);
//		// Save the new Lemonade Entity
//		// Map newly saved Entity, saved with a generated ID, to a ResponseDTO and return it
//		return new ResponseEntity<>(lemonadeMapper.entityToResponseDto(lemonadeRepository.saveAndFlush(lemonadeToSave)), HttpStatus.OK);
//	}
//
//	@Override																						// ResponseEntity<T>(body, statusCode)
//	public ResponseEntity<LemonadeResponseDto> getLemonadeById(Long id) {							// ResponseEntity<T> where T is the return type. allows us to control HTTP response
//		// storing a lemonade with a specific id in an Optional object
//		Optional<Lemonade> optionalLemonade = lemonadeRepository.findById(id);						// Derived Query .findById() is in the Repo
//		// Optional.isEmpty() to check if it is null																							
//		if(optionalLemonade.isEmpty()){																// ResponseEntity<T>(statusCode)  its overloaded! many arg options! :)
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);										// Specifying a status code as part of the returned ResponseEntity's args
//		}																							// see Spring.io documentation for more options to control head/body/status	
//		// Optional.get() to pull the lemonade object out of the Optional object
//		return new ResponseEntity<>(lemonadeMapper.entityToResponseDto(optionalLemonade.get()), HttpStatus.OK);
//	}	// better practice to use Optional in place of basic null checks
//}
		//!!!getRefById will throw its own exception, so we'll add a custom ById method in the repo!!!