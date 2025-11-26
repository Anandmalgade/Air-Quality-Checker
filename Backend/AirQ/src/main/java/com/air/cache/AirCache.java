package com.air.cache;

import java.util.HashMap;
import java.util.Map;

import com.air.dto.AirDTO;


public class AirCache {

	private static final long EXIPRY_MS=10*60*1000;
	private final Map<String,CacheEntry>cache=new HashMap<>();
	private final int maxSize=50;


	private static class CacheEntry{
		AirDTO data;
		long timestamp;
	}
	
	public synchronized AirDTO get(String  city) {
		if(!cache.containsKey(city))
			return null;
		CacheEntry entry=cache.get(city);
		if(System.currentTimeMillis()-entry.timestamp>EXIPRY_MS) {
			cache.remove(city);
			return null;
		}
		return entry.data;
	}
	
	public synchronized void put(String city,AirDTO data) {
		if(cache.size()>=maxSize)
			cache.remove(cache.keySet().iterator().next());
		    CacheEntry entry=new CacheEntry();
		    entry.data=data;
		    entry.timestamp=System.currentTimeMillis();
		    cache.put(city, entry);
	}

}
