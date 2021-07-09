package ftn.uns.ProbaProjekat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.OdradjenTermin;
import ftn.uns.ProbaProjekat.repository.OdradjenTerminRepository;
import ftn.uns.ProbaProjekat.service.OdradjenTerminService;

@Service
public class OdradjenTerminServiceImpl implements OdradjenTerminService {

	public final OdradjenTerminRepository odradjenRepo;
	
	@Autowired
	public OdradjenTerminServiceImpl(OdradjenTerminRepository odradjenRepo) {
		this.odradjenRepo = odradjenRepo;
	}
	
	@Override
	public OdradjenTermin create(OdradjenTermin odradjenTermin) throws Exception {
		if (odradjenTermin.getId() != null) {
			throw new Exception("ID must be null");
		}
		
		OdradjenTermin noviOdradjenTermin = this.odradjenRepo.save(odradjenTermin);
		return noviOdradjenTermin;
	}

}
