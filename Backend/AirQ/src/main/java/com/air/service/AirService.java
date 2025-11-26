package com.air.service;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.air.cache.AirCache;
import com.air.dto.AirDTO;

@Service
public class AirService {

	@Value("${aqi.api.token}")
	private String token;
	
	@Value("${aqi.api.base}")
	private String baseURL;
	
	private final AirCache cache=new AirCache();
	private final RestTemplate rest=new RestTemplate();
	
	
	public AirDTO getAQI(String city)throws Exception{
		AirDTO cached=cache.get(city);
		
		if(cached!=null)
		return cached;
		
		String url=baseURL+city+"/?token="+token;
		String response=rest.getForObject(url,String.class);
		
		 JSONObject json = new JSONObject(response);
		 
		 if(!json.getString("status").equals("ok"))
			 throw new  Exception("City not found");
		 
		 AirDTO dto=new AirDTO();
		 dto.setCity(json.getJSONObject("data").getJSONObject("city").getString("name"));
		 dto.setAqi(json.getJSONObject("data").getInt("aqi"));
		 dto.setPollutants(json.getJSONObject("data").getJSONObject("iaqi").toMap());
         dto.setCategory(getCategory(dto.getAqi()));		
	  
         cache.put(city, dto);
         //System.out.println(dto);

         return dto;
	}
	
	private String getCategory(int aqi) {
		if(aqi<=50)return "Good";
		if(aqi<=100)return "Moderate";
		if(aqi<=150)return "Unhealthy for Sensitive Groups";
		if(aqi<=200)return "Unhealthy";
		return "Hazardus";
	}
	
}

