package com.cooksys.lemonadestand.services.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.exceptions.BadRequestException;
import com.cooksys.lemonadestand.exceptions.NotFoundException;
import com.cooksys.lemonadestand.mappers.LemonadeStandMapper;
import com.cooksys.lemonadestand.model.LemonadeStandRequestDto;
import com.cooksys.lemonadestand.model.LemonadeStandResponseDto;
import com.cooksys.lemonadestand.repositories.LemonadeStandRepository;
import com.cooksys.lemonadestand.services.LemonadeStandService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LemonadeStandServiceImpl implements LemonadeStandService{
	
	private LemonadeStandRepository lemonadeStandRepository;
	private final LemonadeStandMapper lemonadeStandMapper;
	
//============================================================================================================================================================		
	
	private void validateLemonadeStandRequest(LemonadeStandRequestDto lemonadeStandDto){
		
		if(lemonadeStandDto.getName() == null){
					
			throw new BadRequestException("All fields are required on a customer request DTO.");     
		}
	}

//============================================================================================================================================================	

	private LemonadeStand getLemonadeStand(Long id){
			
		Optional<LemonadeStand> optionalLemonadeStand = lemonadeStandRepository.findById(id);
				
		if(optionalLemonadeStand.isEmpty()){
			throw new NotFoundException("No customer found with id " + id + ". Please verify the id number and try again.");
		}
			
		return optionalLemonadeStand.get();
	}	
		
//============================================================================================================================================================		
		
	@Override
	public LemonadeStandResponseDto createLemonadeStand(LemonadeStandRequestDto lemonadeStandRequestDto) {
		
		validateLemonadeStandRequest(lemonadeStandRequestDto);
					
		return lemonadeStandMapper.lemonadeStandToResponseDto(lemonadeStandRepository.saveAndFlush
			  (lemonadeStandMapper.lemonadeStandRequestDtoToEntity(lemonadeStandRequestDto)));
	}

//============================================================================================================================================================	
		
	@Override
	public List<LemonadeStandResponseDto> getAllLemonadeStands(){
			
		return lemonadeStandMapper.entitiesToResponseDtos(lemonadeStandRepository.findAll());
	}

//============================================================================================================================================================	
		
	@Override
	public LemonadeStandResponseDto getLemonadeStandById(Long id) {
		
		return lemonadeStandMapper.lemonadeStandToResponseDto(getLemonadeStand(id));
	}

//============================================================================================================================================================		
		
	@Override
	public LemonadeStandResponseDto updateLemonadeStand(Long id, LemonadeStandRequestDto lemonadeStandRequestDto) {
		//using PATCH to update here, hence why there's no validation check & we have conditions to update single fields
		LemonadeStand lemonadeStandToUpdate = getLemonadeStand(id);
			
		if(lemonadeStandRequestDto.getName() != null){
			lemonadeStandToUpdate.setName(lemonadeStandRequestDto.getName());
		}
			
		return lemonadeStandMapper.lemonadeStandToResponseDto(lemonadeStandRepository.saveAndFlush(lemonadeStandToUpdate));
	}

//============================================================================================================================================================	
		
	@Override
	public LemonadeStandResponseDto deleteLemonadeStand(Long id) {
			
		LemonadeStand lemonadeStandToDelete = getLemonadeStand(id);
			
		lemonadeStandRepository.delete(lemonadeStandToDelete);
			
		return lemonadeStandMapper.lemonadeStandToResponseDto(lemonadeStandToDelete);
	}
}
