package com.motor.service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motor.service.entity.Motor;

@Repository
public interface MotorRepository extends JpaRepository<Motor, Integer> {

	public List<Motor> findByUserId(int userId);

}
