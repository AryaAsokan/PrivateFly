package com.javapointers.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javapointers.models.aircraft;
import com.javapointers.services.aircraftService;

public class aircraftController {
	
	@Autowired
    private aircraftService aircraftService;
	
	@RequestMapping("/")
    public String viewHome() {
        return "index";
    }
	@RequestMapping(value = "/viewAll")
    public String viewAllJets(ModelMap map) {
    	Iterable <aircraft> aircrafts= aircraftService.listAll();
    	map.addAttribute("aircrafts", aircrafts);
        return "/viewAll";
    }

}
