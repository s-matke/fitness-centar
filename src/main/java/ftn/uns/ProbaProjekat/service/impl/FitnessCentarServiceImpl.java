package ftn.uns.ProbaProjekat.service.impl;

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
	
	
}
