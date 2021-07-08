package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.Sala;

public interface SalaService {
	
	Sala findOne(Long id);
	
	List<Sala> findAll();
	
	List<Sala> findByCentar(Long id);
	
	Sala update(Sala sala) throws Exception;
	
	public void delete(Long id);
	
	Sala create(Sala sala) throws Exception;
}
