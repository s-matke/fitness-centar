package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.FitnessCentar;

public interface FitnessCentarService {
	
	FitnessCentar create(FitnessCentar fitnessCentar) throws Exception;
	
	List<FitnessCentar> findAll();
	
	FitnessCentar findOne(Long id);
	
	public void delete(Long id);
}
