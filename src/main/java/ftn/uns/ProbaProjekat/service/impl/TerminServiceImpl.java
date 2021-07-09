package ftn.uns.ProbaProjekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.Termin;
import ftn.uns.ProbaProjekat.repository.TerminRepository;
import ftn.uns.ProbaProjekat.service.TerminService;

@Service
public class TerminServiceImpl implements TerminService {
	
	@Autowired
	public TerminRepository terminRepo;
	
	@Override
	public List<Termin> findAll() {
		List<Termin> termini = this.terminRepo.findAll();
		return termini;
	}
	
	@Override
	public Termin findOne(Long id) {
		Termin termin = this.terminRepo.getOne(id);
		return termin;
	}
	
	@Override
	public Termin create(Termin termin) throws Exception {
		if (termin.getId() != null) {
			throw new Exception("ID must be mull!");
		}
		
		Termin noviTermin = this.terminRepo.save(termin);
		
		return noviTermin;
	}

	@Override
	public List<Termin> findAllByTrener(Long id) {
		List<Termin> listaTermina = this.terminRepo.findAllByTrener(id);
		return listaTermina;
	}

	@Override
	public Termin update(Termin termin) throws Exception {
		Termin terminToUpdate = this.terminRepo.getOne(termin.getId());
		if (terminToUpdate == null) {
			throw new Exception("Termin ne postoji");
		}
		
		terminToUpdate.setCena(termin.getCena());
		terminToUpdate.setPocetak(termin.getPocetak());
		terminToUpdate.setKraj(termin.getKraj());
		terminToUpdate.setSala(termin.getSala());
		terminToUpdate.setTrening(termin.getTrening());
		
		Termin savedTermin = this.terminRepo.save(terminToUpdate);
		return savedTermin;
	}

	@Override
	public void delete(Long id) {
		this.terminRepo.deleteById(id);
	}

}
