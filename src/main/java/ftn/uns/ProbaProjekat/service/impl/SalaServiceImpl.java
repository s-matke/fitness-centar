package ftn.uns.ProbaProjekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.Sala;
import ftn.uns.ProbaProjekat.repository.SalaRepository;
import ftn.uns.ProbaProjekat.service.SalaService;

@Service
public class SalaServiceImpl implements SalaService {
	
	public final SalaRepository salaRepo;
	
	@Autowired
	public SalaServiceImpl(SalaRepository salaRepo) {
		this.salaRepo = salaRepo;
	}
	
	@Override
	public Sala findOne(Long id) {
		Sala sala = this.salaRepo.getOne(id);
		return sala;
	}
	
	@Override
	public List<Sala> findAll(){
		List<Sala> sale = this.salaRepo.findAll();
		return sale;
	}
	
	@Override
	public List<Sala> findByCentar(Long id) {
		List<Sala> sale = this.salaRepo.findAllByFitnessCentar_id(id);
		return sale;
	}
}
