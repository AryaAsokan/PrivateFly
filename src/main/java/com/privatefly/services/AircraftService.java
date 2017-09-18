package com.privatefly.services;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.stereotype.Service;

import com.privatefly.models.Aircraft;


@Service
public class AircraftService {
	 private static List<Aircraft> aircraft = new ArrayList<Aircraft>();
	    public List<Aircraft> fetchAll(String aircraft_name) {
	        List<Aircraft> selected_aircraft = new ArrayList<Aircraft>();
	        for (Aircraft aircraft : aircraft) {
	            if (aircraft.getAircraft_name().equalsIgnoreCase(aircraft_name)) {
	            	selected_aircraft.add(aircraft);
	            }
	        }
	        return selected_aircraft;
	    }
	    public List<Aircraft> listAll() {
	        
	        return aircraft;
	    }
	    
	    public List<Aircraft> sort() {
	    	return aircraft;
	    }
}
