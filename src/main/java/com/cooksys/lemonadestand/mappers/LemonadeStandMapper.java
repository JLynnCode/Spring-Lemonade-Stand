package com.cooksys.lemonadestand.mappers;

import java.util.List;

import org.mapstruct.Mapper;

import com.cooksys.lemonadestand.entities.LemonadeStand;
import com.cooksys.lemonadestand.model.LemonadeStandRequestDto;
import com.cooksys.lemonadestand.model.LemonadeStandResponseDto;

@Mapper(componentModel="Spring")
public interface LemonadeStandMapper {
	
	LemonadeStand lemonadeStandRequestDtoToEntity(LemonadeStandRequestDto lemonadeStandRequestDto);
	
	LemonadeStand lemonadeStandDtoToEntity(LemonadeStandRequestDto lemonadeStandDto);
	
	LemonadeStandResponseDto lemonadeStandToResponseDto(LemonadeStand lemonadeStand);
	List<LemonadeStandResponseDto> entitiesToResponseDtos(List<LemonadeStand> lemonadeStands);
}
