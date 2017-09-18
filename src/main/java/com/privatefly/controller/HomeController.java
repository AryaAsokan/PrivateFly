package com.privatefly.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.privatefly.models.Aircraft;
import com.privatefly.models.AircraftDao;
import com.privatefly.services.AircraftService;

import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

@Controller
public class HomeController {

	private static final Logger logger = Logger.getLogger(HomeController.class);
	@Autowired
	private AircraftDao aircraftDao;

	@Autowired
	public AircraftService aircraftService;
	

	@RequestMapping(value = "/viewAll")
	public String addNewAircraft() {
		return "viewAll";
	}
}
