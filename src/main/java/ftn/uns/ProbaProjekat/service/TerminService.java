package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.Termin;

public interface TerminService {
	
	List<Termin> findAll();
	
	Termin findOne(Long id);
	
	Termin create(Termin termin) throws Exception;
	
}
