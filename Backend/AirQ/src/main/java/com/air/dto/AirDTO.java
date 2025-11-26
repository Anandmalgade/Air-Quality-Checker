package com.air.dto;

import java.util.Map;

import lombok.Data;

@Data
public class AirDTO {

	private String city;
	private int aqi;
	private Map<String,Object>pollutants;
	private String category;
	
}
