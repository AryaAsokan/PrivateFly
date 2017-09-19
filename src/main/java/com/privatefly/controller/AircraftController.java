package com.privatefly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.privatefly.models.Aircraft;
import com.privatefly.models.AircraftDao;
import com.privatefly.services.AircraftService;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@RestController
public class AircraftController {

	private static final Logger logger = Logger.getLogger(AircraftController.class);
	@Autowired
	private AircraftDao aircraftDao;

	@Autowired
	public AircraftService aircraftService;

	@RequestMapping(value = "/aircraft", method = RequestMethod.POST)
	public  ResponseEntity<String> create(@RequestParam("aircraftName") String aircraftname,
			@RequestParam("airfield") String airfieldname, @RequestParam("ICAO_code") String ICAOcode,
			@RequestParam("openedDate") String openedDate, @RequestParam("runway_length") String runwaylength) {
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			Date date = new Date();
			try {
				date = df.parse(openedDate);
			} catch (ParseException e) {
				logger.error(e);
			}
			aircraftService.createNewAircraft(aircraftname,airfieldname,ICAOcode,date,runwaylength);
			logger.info("New aircraft is saved!");
			return new ResponseEntity<String>(HttpStatus.CREATED);
			
	}

	@RequestMapping(value = "/listAll")
	public ResponseEntity<Iterable<Aircraft>> viewAllJets() {
			Iterable<Aircraft> aircrafts = aircraftService.viewAllAircrafts();
			logger.info("All aircrafts details are fetched!");
			return new ResponseEntity<Iterable<Aircraft>>(aircrafts, HttpStatus.OK);
	}

	@RequestMapping(value = "/sort")
	public ResponseEntity<Iterable<Aircraft>>  sortAll() {
			Iterable<Aircraft> aircrafts = aircraftService.sortedAircrafts();
			logger.info("All aircrafts details are sorted!");
			return new ResponseEntity<Iterable<Aircraft>>(aircrafts, HttpStatus.OK);
		
	}

	@RequestMapping(value = "/aircraft/{aircraftName}", method = RequestMethod.GET)
	public ResponseEntity<Aircraft> listOne(@PathVariable("aircraftName") String aircraftName) {
		logger.info("Searching");
		return new ResponseEntity<Aircraft>(aircraftService.searchAircraft(aircraftName), HttpStatus.OK);
		
	
	}

}

