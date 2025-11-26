package com.air.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.air.dto.AirDTO;
import com.air.service.AirService;

@RestController
@RequestMapping("/api/air")
@CrossOrigin(origins = "*") 
public class AirController {

	private final AirService service;

	public AirController(AirService service) {
		this.service = service;
	}
	
	@GetMapping("/{city}")
	public AirDTO getAQI(@PathVariable String city)throws Exception
	{
		  return service.getAQI(city);
}
	@GetMapping
	public String getMsg() {
		return "Anand";
	}
}
