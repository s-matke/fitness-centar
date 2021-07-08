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

	@Override
	public Sala update(Sala sala) throws Exception {
		Sala salaToUpdate = this.salaRepo.getOne(sala.getId());
		if (salaToUpdate == null) {
			throw new Exception("Sala ne postoji");
		}
		
		salaToUpdate.setOznaka(sala.getOznaka());
		salaToUpdate.setKapacitet(sala.getKapacitet());
		salaToUpdate.setFitnessCentar(sala.getFitnessCentar());
		
		Sala savedSala = this.salaRepo.save(salaToUpdate);
		return savedSala;
	}

	@Override
	public void delete(Long id) {
		this.salaRepo.deleteById(id);
		
	}

	@Override
	public Sala create(Sala sala) throws Exception {
		if (sala.getId() != null) {
			throw new Exception("ID must be null!");
		}
		Sala novaSala = this.salaRepo.save(sala);
		return novaSala;
	}
}
