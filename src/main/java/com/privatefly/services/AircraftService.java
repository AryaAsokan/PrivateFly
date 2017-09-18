package com.privatefly.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.privatefly.models.Aircraft;
import com.privatefly.models.AircraftDao;

@Service
public class AircraftService {
	@Autowired
	private AircraftDao aircraftDao;
	
	
	public Iterable<Aircraft> viewAllAircrafts(){
		return aircraftDao.findAll();
		}
	
	public Iterable<Aircraft> sortedAircrafts(){
		return aircraftDao.findAllByOrderByAirfieldAsc();
		}
	
	
	public void createNewAircraft(String aircraftname,String airfieldname,String ICAOcode,Date openedDate,String runwaylength){
		Aircraft aircraft = new Aircraft(aircraftname, airfieldname, ICAOcode, openedDate, runwaylength);
		aircraftDao.save(aircraft);
		}
	
	
	public Aircraft searchAircraft(String airfield){
		return aircraftDao.findByAircraftname(airfield);
	}
	
}
