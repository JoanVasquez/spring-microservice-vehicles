package com.motor.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.motor.service.entity.Motor;
import com.motor.service.repository.MotorRepository;

@Service
public class MotorService {

	@Autowired
	private MotorRepository motorRepository;

	public List<Motor> getALl() {
		return this.motorRepository.findAll();
	}

	public Motor getById(int id) {
		return this.motorRepository.findById(id).orElse(null);
	}

	public Motor save(Motor motor) {
		return this.motorRepository.save(motor);
	}

	public List<Motor> getByUserId(int userId) {
		return this.motorRepository.findByUserId(userId);
	}
}
