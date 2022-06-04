package com.car.service.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.car.service.entity.Car;
import com.car.service.repository.CarRepository;

@Service
public class CarService {

	@Autowired
	private CarRepository carRepository;

	public List<Car> getALl() {
		return this.carRepository.findAll();
	}

	public Car getById(int id) {
		return this.carRepository.findById(id).orElse(null);
	}

	public Car save(Car car) {
		return this.carRepository.save(car);
	}

	public List<Car> getByUserId(int userId) {
		return this.carRepository.findByUserId(userId);
	}
}
