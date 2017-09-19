package com.privatefly.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.privatefly.Application;
import com.privatefly.controller.AircraftController;
import com.privatefly.models.Aircraft;
import com.privatefly.services.AircraftService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {Application.class})
public class PrivateFlyTest {

    private MockMvc mockMvc;

    @Mock
    private AircraftService aircraftService;

    @InjectMocks
    private AircraftController aircraftController;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(aircraftController)
                .build();
    }

    // =========================================== Get All Aircrafts ==========================================

    @Test
    public void test_get_all_success() throws Exception {
    	Date openedDate = new Date();
        List<Aircraft> aircrafts = Arrays.asList(
        		new Aircraft("Boeying 787", "Logan Country Airport", "KAAA", openedDate, "1000ft"),
        		new Aircraft("Wright Flyer", "Apalachicola Regional Airport ", "KAAF (AAF)", openedDate,
        				"1000ft"));

        when(aircraftService.viewAllAircrafts()).thenReturn(aircrafts);

        mockMvc.perform(get("/listAll"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].aircraft_name", is("Boeying 787")))
                .andExpect(jsonPath("$[0].airfield", is("Logan Country Airport")))
                .andExpect(jsonPath("$[0].icao_code", is("KAAA")))
                .andExpect(jsonPath("$[0].runway_length", is("1000ft")))
                .andExpect(jsonPath("$[1].aircraft_name", is("Wright Flyer")))
                .andExpect(jsonPath("$[1].airfield", is("Apalachicola Regional Airport ")))
                .andExpect(jsonPath("$[1].icao_code", is("KAAF (AAF)")))
				.andExpect(jsonPath("$[1].runway_length", is("1000ft")))
				;
        
        //mockMvc.perform(null);

        verify(aircraftService, times(1)).viewAllAircrafts();
        verifyNoMoreInteractions(aircraftService);
    }

	// =========================================== Get Aircraft By Name=========================================


	@Test
	public void test_get_by_airfieldname() throws Exception {
		Date openedDate = new Date();
		Aircraft aircraft = new Aircraft("Boeying 787", "Logan Country Airport", "KAAA", openedDate, "1000ft");

		when(aircraftService.searchAircraft("Boeying 787")).thenReturn(aircraft);

		mockMvc.perform(get("/aircraft/{aircraftName}", "Boeying 787")).andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
				.andExpect(jsonPath("$.aircraft_name", is("Boeying 787")))
				.andExpect(jsonPath("$.airfield", is("Logan Country Airport")))
				.andExpect(jsonPath("$.openedDate", is(openedDate.getTime())))
				.andExpect(jsonPath("$.icao_code", is("KAAA")))
				.andExpect(jsonPath("$.runway_length", is("1000ft")));

	}

    // =========================================== Create New Aircraft ========================================

    @Test
    public void test_create_aircraft_success() throws Exception {
    	Date openedDate = new Date();
        Aircraft aircraft = new Aircraft("Boeying 787", "Logan Country Airport", "KAAA", openedDate, "1000ft");

        when(aircraftService.exists("Boeying 787")).thenReturn(false);
        doNothing().when(aircraftService).createNewAircraft("Boeying 787", "Logan Country Airport", "KAAA", openedDate, "1000ft");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        mockMvc.perform(
                post("/aircraft")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .param("aircraftName","Boeying 787")
                        .param("airfield", "Logan Country Airport")
        				.param("ICAO_code", "KAAA")
        				.param("openedDate", df.format(openedDate))
        				.param("runway_length", "1000ft"))
        				.andExpect(status().isCreated());
    }

}
