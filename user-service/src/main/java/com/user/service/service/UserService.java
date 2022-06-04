package com.user.service.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.user.service.entity.User;
import com.user.service.feignclients.CarFeignClient;
import com.user.service.feignclients.MotorFeignClient;
import com.user.service.model.Car;
import com.user.service.model.Motor;
import com.user.service.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private CarFeignClient carFeignClient;

	@Autowired
	private MotorFeignClient motorFeignClient;

	@Autowired
	private UserRepository userRepository;

	@SuppressWarnings("unchecked")
	public List<Car> getCars(int userId) {
		List<Car> cars = this.restTemplate.getForObject("http://localhost:8002/car/user/" + userId, List.class);
		return cars;
	}

//	@SuppressWarnings("unchecked")
//	public List<Motor> getMotors(int userId) {
//		List<Motor> motors = this.restTemplate.getForObject("http://localhost:8003/motor/user/" + userId, List.class);
//		return motors;
//	}

	public Car saveCar(int userId, Car car) {
		car.setUserId(userId);
		Car newCar = carFeignClient.save(car);
		return newCar;
	}

	public Motor saveMotor(int userId, Motor motor) {
		motor.setUserId(userId);
		Motor newMotor = motorFeignClient.save(motor);
		return newMotor;
	}

	public List<Motor> getMotors(int userId) {
		return motorFeignClient.getMotors(userId);
	}

	public List<User> getALl() {
		return this.userRepository.findAll();
	}

	public User getById(int id) {
		return this.userRepository.findById(id).orElse(null);
	}

	public User save(User user) {
		return this.userRepository.save(user);
	}

	public Map<String, Object> getUserAndVehicles(int userId) {
		List<Motor> motors = motorFeignClient.getMotors(userId);
		List<Car> cars = carFeignClient.getCars(userId);
		Map<String, Object> result = new HashMap<>();

		User user = getById(userId);

		if (user == null) {
			result.put("msg", "User not found");
			return result;
		}

		result.put("user", user);

		if (cars.isEmpty()) {
			result.put("cars", "This user does not have any car");
		} else {
			result.put("cars", cars);
		}

		if (motors.isEmpty()) {
			result.put("motors", "This user does not have any motorr");
		} else {
			result.put("motors", motors);
		}

		return result;
	}
}
