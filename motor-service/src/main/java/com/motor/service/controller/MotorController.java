package com.motor.service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motor.service.entity.Motor;
import com.motor.service.service.MotorService;

@RestController
@RequestMapping("/motor")
public class MotorController {

	@Autowired
	private MotorService motorService;

	@GetMapping
	public ResponseEntity<List<Motor>> listMotor() {
		List<Motor> motors = this.motorService.getALl();
		if (motors.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(motors);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Motor> getCar(@PathVariable("id") int id) {
		Motor motor = this.motorService.getById(id);
		if (motor == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(motor);
	}

	@PostMapping
	public ResponseEntity<Motor> saveCar(@RequestBody Motor motor) {
		Motor newMotor = this.motorService.save(motor);
		return ResponseEntity.ok(newMotor);
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<List<Motor>> getCarsByUser(@PathVariable("userId") int userId) {
		List<Motor> motors = this.motorService.getByUserId(userId);
		if (motors.isEmpty()) {
			return ResponseEntity.noContent().build();
		}

		return ResponseEntity.ok(motors);
	}
}
