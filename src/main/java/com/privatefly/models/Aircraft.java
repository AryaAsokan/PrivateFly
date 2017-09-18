package com.privatefly.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="aircrafts")
public class Aircraft {
	@Id
	  @GeneratedValue(strategy = GenerationType.AUTO)
	  private long id;
	@NotNull
    private String aircraftname;
	@NotNull
    private String airfield;
	@NotNull
    private Date openedDate;
	@NotNull
    private String runway_length;
	@NotNull
    private String ICAO_code;

    public Aircraft() {
    	super();
    }
    
    public Aircraft(String aircraftname, String airfield, String ICAO_code, Date openedDate,String runway_length) {
		super();
	    //this.id = id;
	    this.aircraftname = aircraftname;
	    this.airfield = airfield;
	    this.ICAO_code = ICAO_code;
	    this.openedDate = openedDate;
	    this.runway_length = runway_length;
    }

	public long getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAircraft_name() {
		return aircraftname;
	}

	public void setAircraft_name(String aircraft_name) {
		this.aircraftname = aircraft_name;
	}

	public String getAirfield() {
		return airfield;
	}

	public void setAirfield(String airfield) {
		this.airfield = airfield;
	}

	public Date getOpenedDate() {
		return openedDate;
	}

	public void setOpenedDate(Date openedDate) {
		this.openedDate = openedDate;
	}

	public String getRunway_length() {
		return runway_length;
	}

	public void setRunway_length(String runway_length) {
		this.runway_length = runway_length;
	}

	public String getICAO_code() {
		return ICAO_code;
	}

	public void setICAO_code(String iCAO_code) {
		ICAO_code = iCAO_code;
	}
	@Override
    public String toString() {
        return String.format(
                "{id=%s, aircraft_name=%s, airfield=%s, ICAO_code=%s, openedDate=%s, runway_length=%s}", id,
                aircraftname, airfield, ICAO_code, openedDate,runway_length);
    }
    
}
