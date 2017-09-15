package com.javapointers.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;
import com.javapointers.models.aircraft;


@Service
public class aircraftService {
	 private static List<aircraft> aircraft = new ArrayList<aircraft>();
	    private static int aircraftCount = 3;

//	    static {
//	    	aircraft.add(new aircraft(1001, "Wright Flyer", "Logan County Airport","KAAA", new Date(),
//	        		"1 km"));
//	        aircraft.add(new aircraft(2, "Boeing 787", "Apalachicola Regional Airport","KAAF (AAF)", new Date(),
//	        		"2 km "));
//	        aircraft.add(new aircraft(3, "Cirrus SR22", "Colonel James Jabara Airpor","KAAO", new Date(),
//	        		"1 km"));
//	    }
	    public List<aircraft> fetchAll(String aircraft_name) {
	        List<aircraft> selected_aircraft = new ArrayList<aircraft>();
	        for (aircraft aircraft : aircraft) {
	            if (aircraft.getAircraft_name().equalsIgnoreCase(aircraft_name)) {
	            	selected_aircraft.add(aircraft);
	            }
	        }
	        return selected_aircraft;
	    }
	    public List<aircraft> listAll() {
	        
	        return aircraft;
	    }
	    
	    public List<aircraft> sort() {
	    	return aircraft;
	    }
}
