package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.Trening;

public interface TreningService {

	List<Trening> findAll(String keyword);
	
	List<Trening> findByNaziv(String naziv);
	
	List<Trening> findByTrajanje(Integer trajanje);
	
	List<Trening> findByOpis(String opis);
	
	List<Trening> findByTip(String tip);
	
	Trening create(Trening trening) throws Exception;
	
	List<Trening> findByTrener(Long id);

	Trening findOne(Long id);
}
