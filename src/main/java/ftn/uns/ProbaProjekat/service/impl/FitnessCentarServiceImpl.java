package ftn.uns.ProbaProjekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.FitnessCentar;
import ftn.uns.ProbaProjekat.repository.FitnessCentarRepository;
import ftn.uns.ProbaProjekat.service.FitnessCentarService;

@Service
public class FitnessCentarServiceImpl implements FitnessCentarService {

	public final FitnessCentarRepository fitnessRepo;
	
	@Autowired
	public FitnessCentarServiceImpl(FitnessCentarRepository fitnessRepo) {
		this.fitnessRepo = fitnessRepo;
	}
	
	@Override
	public FitnessCentar create(FitnessCentar fitnessCentar) throws Exception {
		if (fitnessCentar.getId() != null) {
			throw new Exception("ID must be null!");
		}
		FitnessCentar noviFitnessCentar = this.fitnessRepo.save(fitnessCentar);
		return noviFitnessCentar;
	}
	
	@Override
	public List<FitnessCentar> findAll() {
		List<FitnessCentar> centri = this.fitnessRepo.findAll();
		return centri;
	}
	
	@Override
	public FitnessCentar findOne(Long id) {
		FitnessCentar centar = this.fitnessRepo.getOne(id);
		return centar;
	}

	@Override
	public void delete(Long id) {
		this.fitnessRepo.deleteById(id);
		
	}

	@Override
	public FitnessCentar update(FitnessCentar centar) throws Exception {
		FitnessCentar centarToUpdate = this.fitnessRepo.getOne(centar.getId());
		if (centarToUpdate == null) {
			throw new Exception("Centar ne postoji");
		}
		
		centarToUpdate.setNaziv(centar.getNaziv());
		centarToUpdate.setAdresa(centar.getAdresa());
		centarToUpdate.setTelefon(centar.getTelefon());
		centarToUpdate.setEmail(centar.getEmail());
		
		FitnessCentar savedCentar = this.fitnessRepo.save(centarToUpdate);
		return savedCentar;
	}
	
	
}
