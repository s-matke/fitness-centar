package ftn.uns.ProbaProjekat.service.impl;

import java.util.List;

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

	@Override
	public List<OdradjenTermin> findByGradedClan(Long id) {
		if (id == null) {
			return null;
		}
		
		List<OdradjenTermin> odradjeniTermini = this.odradjenRepo.findAllByGradedClan(id);
		return odradjeniTermini;
	}

	@Override
	public List<OdradjenTermin> findByUngradedClan(Long id) {
		if (id == null) {
			return null;
		}
		
		List<OdradjenTermin> odradjeniTermini = this.odradjenRepo.findAllByUngradedClan(id);
		return odradjeniTermini;
	}

	@Override
	public OdradjenTermin update(OdradjenTermin odradjen) throws Exception {
		OdradjenTermin odradjenToUpdate = this.odradjenRepo.getOne(odradjen.getId());
		
		if (odradjenToUpdate == null) {
			throw new Exception("Odradjen termin ne postoji");
		}
		
		odradjenToUpdate.setOcena(odradjen.getOcena());
		
		OdradjenTermin odradjenT = this.odradjenRepo.save(odradjenToUpdate);
		
		return odradjenT;
	}

	@Override
	public OdradjenTermin findOne(Long id) {
		OdradjenTermin odradjen = this.odradjenRepo.getOne(id);
		return odradjen;
	}

}
