package com.cooksys.lemonadestand.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cooksys.lemonadestand.entities.LemonadeStand;

@Repository
public interface LemonadeStandRepository extends JpaRepository<LemonadeStand, Long> {

	Optional<LemonadeStand> findById(Long id);
}
