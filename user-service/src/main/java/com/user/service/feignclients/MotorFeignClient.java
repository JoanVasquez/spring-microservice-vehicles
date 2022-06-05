package com.user.service.feignclients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import com.user.service.model.Motor;

@FeignClient(name = "motor-service")
@RequestMapping("/motor")
public interface MotorFeignClient {

	@PostMapping
	public Motor save(@RequestBody Motor motor);

	@GetMapping("/user/{userId}")
	public List<Motor> getMotors(@PathVariable("userId") int userId);
}
