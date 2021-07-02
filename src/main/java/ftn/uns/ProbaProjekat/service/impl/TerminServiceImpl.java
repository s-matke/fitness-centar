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
}
