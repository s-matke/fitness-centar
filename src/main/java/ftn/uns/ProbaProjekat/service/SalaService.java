package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.Sala;

public interface SalaService {
	
	Sala findOne(Long id);
	
	List<Sala> findAll();
}
