package com.javapointers.models;

import org.springframework.data.repository.CrudRepository;

public interface aircraftDao extends CrudRepository<aircraft, Long> {

	public aircraft findByAirfield(String airfield);
	public Iterable<aircraft> findAllByOrderByAirfieldAsc();
	public aircraft findByAircraftname(String aircraftname);
}
