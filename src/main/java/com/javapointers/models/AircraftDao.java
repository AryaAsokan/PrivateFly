package com.javapointers.models;

import org.springframework.data.repository.CrudRepository;

public interface AircraftDao extends CrudRepository<Aircraft, Long> {

	public Aircraft findByAirfield(String airfield);
	public Iterable<Aircraft> findAllByOrderByAirfieldAsc();
	public Aircraft findByAircraftname(String aircraftname);
}
