package com.user.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.user.service.model.Car;

@FeignClient(name = "car-service")
@RequestMapping("/car")
public interface CarFeignClient {

	@PostMapping
	public Car save(@RequestBody Car car);
	
	@GetMapping("/user/{userId}")
	public List<Car> getCars(@PathVariable("userId") int userId);
}
