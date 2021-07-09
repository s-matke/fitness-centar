package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.OdradjenTermin;

public interface OdradjenTerminService {
	

	OdradjenTermin create(OdradjenTermin odradjenTermin) throws Exception;
	
	List<OdradjenTermin> findByGradedClan(Long id);
	
	List<OdradjenTermin> findByUngradedClan(Long id);
	
	OdradjenTermin update(OdradjenTermin odradjen) throws Exception;
	
	OdradjenTermin findOne(Long id);

	List<OdradjenTermin> findAll(Long id);
	
}
