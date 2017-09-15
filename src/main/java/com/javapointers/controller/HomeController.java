package com.javapointers.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.apache.log4j.Logger;

import com.javapointers.models.aircraft;
import com.javapointers.services.aircraftService;


import com.javapointers.models.aircraftDao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;


@Controller
public class HomeController {
	
	private static final Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	  private aircraftDao aircraftDao;
	
	@Autowired
    public aircraftService aircraftService;
    
    @RequestMapping(value = "/addNew")
    public String addNewAircraft() {
        return "/addNew";
    }
    
    @RequestMapping(value = "/addNewJet", method = RequestMethod.POST)
    public String create(@RequestParam("aircraft-name") String aircraftname , @RequestParam("airfield-name") String airfieldname , @RequestParam("icao-code") String ICAOcode  , @RequestParam("date-opened") Date openedDate, @RequestParam("runway-length") String runwaylength) {
    	//logs debug message
    			if(logger.isDebugEnabled()){
    				logger.debug("Added new Aircraft details!");
    			}
    			//logs exception
    			logger.error("This is Error message", new Exception("Testing"));

    	try {
        	aircraft aircraft = new aircraft(aircraftname, airfieldname,ICAOcode, openedDate, runwaylength);
        	aircraftDao.save(aircraft);
        	return "redirect:/viewAll";
        }
        catch (Exception ex) {
          return "Error creating the user: " + ex.toString();
        }
        //return "/viewAll";
      }
    
    @RequestMapping(value="/viewAll")
    public String viewAllJets(ModelMap map) {
    	if(logger.isDebugEnabled()){
			logger.debug("Retrieved all aircraft details!");
		}
    	//logs exception
    			logger.error("This is Error message", new Exception("Testing"));

      try {
    	Iterable <aircraft> aircrafts = aircraftDao.findAll();
       	map.addAttribute("aircrafts", aircrafts);
      }
      catch(Exception ex) {
        return "User not found";
      }
      return "viewAll";
    }
   
  
    @RequestMapping (value="/sort")
    public String sortAll(ModelMap map) {
    	if(logger.isDebugEnabled()){
			logger.debug("Sorted !");
		}
    	//logs exception
    			logger.error("This is Error message", new Exception("Testing"));

    	try {
    		Iterable <aircraft> aircrafts = aircraftDao.findAllByOrderByAirfieldAsc();
    		map.addAttribute("aircrafts", aircrafts);
    		return "viewAll";
    	}
    	catch(Exception ex) {
    		return "Data not found";
    	}
    }
    
    @RequestMapping (value="/findOne")
    public String listOne(ModelMap map,@RequestParam("aircraft-name") String airfieldname) {
    	if(logger.isDebugEnabled()){
			logger.debug("Searched an aircraft details!");
		}
    	try {
    		aircraft aircraft = new aircraft();
    		map.addAttribute("aircrafts", aircraftDao.findByAircraftname(airfieldname));
    		return "viewAll";
    	}
    	catch(Exception ex) {
    		return "Data not found";
    	}
    }
    
}

