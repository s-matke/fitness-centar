package ftn.uns.ProbaProjekat.service;

import java.sql.Timestamp;
import java.util.List;

import ftn.uns.ProbaProjekat.model.PrijavaTermina;

public interface PrijavaTerminaService {

	PrijavaTermina create(PrijavaTermina prijava) throws Exception;
	
	List<PrijavaTermina> findByClan(Long id);
	
	public void delete(Long id);
	
	PrijavaTermina findOne(Long id);
	
	List<PrijavaTermina> findAllById(Long id);
	
	int slobodnaMesta(Timestamp pocetak, Timestamp kraj);

}
