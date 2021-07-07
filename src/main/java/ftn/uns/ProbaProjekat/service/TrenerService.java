package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.Trener;

public interface TrenerService {
	
	Trener findOne(Long id);
	
	List<Trener> findAll();
	
	Trener create(Trener trener) throws Exception;
	
	Trener update(Trener trener) throws Exception;
		
	List<Trener> findByCentar(Long id);
	
	public void delete(Long id);
}
