package com.cooksys.lemonadestand.mappers;
import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.lemonadestand.entities.Lemonade;
import com.cooksys.lemonadestand.model.LemonadeRequestDto;
import com.cooksys.lemonadestand.model.LemonadeResponseDto;

@Mapper(componentModel = "spring")  		// annotation to let Mapstruct know this is a Mapper. must include listed arg when using Spring
public interface LemonadeMapper {   		// otherwise Mapstruct won't know we're using Spring, but now it knows to make it a component
											// and have Spring manage it. Otherwise we'll get some Bean errors bruh, beans
	
	//Created as an interface, because Mapstruct is implementing all the methods for mapping
	
	// takes a request DTO and returns an entity
	Lemonade requestDtoToEntity(LemonadeRequestDto lemonadeRequestDto);
	Lemonade lemonadeDtoToEntity(LemonadeRequestDto lemonadeRequestDto);
	// takes an entity and returns a response DTO
	LemonadeResponseDto entityToResponseDto(Lemonade lemonade);
	
	// takes a List of entities and returns a list of Response DTOs
	List<LemonadeResponseDto> entitiesToResponseDto(List<Lemonade> lemonades);
}

// !! when you create a Mapper, Mapstruct does not run when Spring runs !!
// !! you have to run a Maven build specifically in order to generate this implementing class !!
// to do that:
//			right click project -> Run As -> Maven Build
//			add goals: clean install
//			save & run :)
// we can then view generated code (*MapperImpl.java) under projectFile -> target -> generated-sources
// !!!! any time you make a change to an Entity or DTO, you need to re-generate Mapstruct Mappers through Maven Build !!!!