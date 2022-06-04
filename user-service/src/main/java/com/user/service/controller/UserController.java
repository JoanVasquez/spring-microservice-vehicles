package com.user.service.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.service.entity.User;
import com.user.service.model.Car;
import com.user.service.model.Motor;
import com.user.service.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<List<User>> listUser() {
		List<User> users = this.userService.getALl();
		if (users.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> getUser(@PathVariable("id") int id) {
		User user = this.userService.getById(id);
		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		return ResponseEntity.ok(user);
	}

	@PostMapping
	public ResponseEntity<User> saveUser(@RequestBody User user) {
		User newUser = this.userService.save(user);
		return ResponseEntity.ok(newUser);
	}

	@GetMapping("/cars/{userId}")
	public ResponseEntity<List<Car>> listCarsByUser(@PathVariable("userId") int userId) {
		User user = this.userService.getById(userId);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Car> cars = this.userService.getCars(user.getId());
//		if (cars.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
		return ResponseEntity.ok(cars);
	}

	@GetMapping("/motors/{userId}")
	public ResponseEntity<List<Motor>> listMotorsByUser(@PathVariable("userId") int userId) {
		User user = this.userService.getById(userId);

		if (user == null) {
			return ResponseEntity.notFound().build();
		}

		List<Motor> motors = this.userService.getMotors(user.getId());
//		if (motors.isEmpty()) {
//			return ResponseEntity.noContent().build();
//		}
		return ResponseEntity.ok(motors);
	}

	@PostMapping("/car/{userId}")
	public ResponseEntity<Car> saveCar(@PathVariable("userId") int userId, @RequestBody Car car) {
		Car newCar = userService.saveCar(userId, car);
		return ResponseEntity.ok(newCar);
	}

	@PostMapping("/motor/{userId}")
	public ResponseEntity<Motor> saveMOotor(@PathVariable("userId") int userId, @RequestBody Motor motor) {
		Motor newMotor = userService.saveMotor(userId, motor);
		return ResponseEntity.ok(newMotor);
	}

	@GetMapping("/vehicles/{userId}")
	public ResponseEntity<Map<String, Object>> getVehicles(@PathVariable("userId") int userId) {
		Map<String, Object> result = this.userService.getUserAndVehicles(userId);
		return ResponseEntity.ok(result);
	}

}
