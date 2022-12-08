package com.cooksys.lemonadestand.services;

import java.util.List;

import com.cooksys.lemonadestand.model.LemonadeStandRequestDto;
import com.cooksys.lemonadestand.model.LemonadeStandResponseDto;

public interface LemonadeStandService {

	List<LemonadeStandResponseDto> getAllLemonadeStands();

	LemonadeStandResponseDto createLemonadeStand(LemonadeStandRequestDto lemonadeStandRequestDto);

	LemonadeStandResponseDto getLemonadeStandById(Long id);

	LemonadeStandResponseDto updateLemonadeStand(Long id, LemonadeStandRequestDto lemonadeStand);

	LemonadeStandResponseDto deleteLemonadeStand(Long id);
}
