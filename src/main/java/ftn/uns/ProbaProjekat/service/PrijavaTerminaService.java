package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.PrijavaTermina;

public interface PrijavaTerminaService {

	PrijavaTermina create(PrijavaTermina prijava) throws Exception;
	
	List<PrijavaTermina> findByClan(Long id);

}
