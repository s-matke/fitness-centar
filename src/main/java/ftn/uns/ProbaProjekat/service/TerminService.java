package ftn.uns.ProbaProjekat.service;

import java.sql.Timestamp;
import java.util.List;

import ftn.uns.ProbaProjekat.model.Termin;

public interface TerminService {
	
	List<Termin> findAll();
	
	Termin findOne(Long id);
	
	Termin create(Termin termin) throws Exception;
	
	List<Termin> findAllByTrener(Long id);
	
	Termin update(Termin termin) throws Exception;
	
	public void delete(Long id);
	
	List<Termin> search(String naziv, String tip_treninga, String opis, Double cenaOd, Double cenaDo, Timestamp dateStart, Timestamp dateEnd);
	
}
