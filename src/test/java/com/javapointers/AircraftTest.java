package com.javapointers;

import static org.junit.Assert.*;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;

import com.javapointers.models.AircraftDao;
import com.javapointers.models.Aircraft;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
// @ContextConfiguration(locations = {"/Test-Context.xml"})

public class AircraftTest {

	@Test
	public void test() {
		fail("Not yet implemented");
	}

	@Autowired
	AircraftDao aircraftDao;

	@Before
	public void setUp() throws Exception {
		aircraftDao.deleteAll();
		Date openedDate = new Date();
		Aircraft aircraft_one = new Aircraft("Boeying 787", "Logan Country Airport", "KAAA", openedDate, "1000ft");
		Aircraft aircraft_two = new Aircraft("Wright Flyer", "Apalachicola Regional Airport ", "KAAF (AAF)", openedDate,
				"1000ft");
		Aircraft aircraft_three = new Aircraft("Supermarine Spitfire", "Wadena Municipal Airport", "KADC", openedDate,
				"1500ft");
		aircraftDao.save(aircraft_one);
		aircraftDao.save(aircraft_two);
		aircraftDao.save(aircraft_three);
	}

	@Test
	public void loadAllAirecrafts() {
		int count = 0;
		Iterable<Aircraft> aircrafts = (Iterable<Aircraft>) aircraftDao.findAll();
		for (Aircraft value : aircrafts) {
			count++;
		}

		assertEquals("Did not get all names", 3, count);
	}

	@Test
	public void testFindAircraft() throws Exception {
		Aircraft aircraft = aircraftDao.findByAircraftname("Wright Flyer");
		assertEquals("Found wrong name", "Wright Flyer", aircraft.getAircraft_name());
	}

	@Test
	public void createAircraft() throws Exception {

		// Create a new aircraft
		Date openedTime = new Date();
		Aircraft aircraft4 = new Aircraft("Cirrus SR22", "Double Eagle II Airport", "KAEG", openedTime,
				"RunwayLength4");
		aircraftDao.save(aircraft4);

		Aircraft aircraft = aircraftDao.findByAircraftname("Cirrus SR22");
		assertEquals("Found wrong name", "Cirrus SR22", aircraft.getAircraft_name());

	}

	@Test
	public void testSortedAirecraftsByAirefieldname() {
		int count = 0;
		List<Aircraft> aircrafts = (List<Aircraft>) aircraftDao.findAllByOrderByAirfieldAsc();
		count = aircrafts.size();
		assertEquals("Did not get all Aircrafts", 3, count);
		assertEquals("Not in sorted list", "Apalachicola Regional Airport", aircrafts.get(0).getAirfield());
		assertEquals("Not in sorted list", "Logan Country Airport", aircrafts.get(1).getAirfield());
		assertEquals("Not in sorted list", "Wadena Municipal Airport", aircrafts.get(3).getAirfield());
	}

}
