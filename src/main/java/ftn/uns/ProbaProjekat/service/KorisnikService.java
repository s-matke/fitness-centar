package ftn.uns.ProbaProjekat.service;

import java.util.List;

import ftn.uns.ProbaProjekat.model.Korisnik;

public interface KorisnikService {
	
	Korisnik findOne(Long id);
	
	List<Korisnik> findAll();
	
	Korisnik create(Korisnik korisnik) throws Exception;
	
//	Clan create(Clan clan) throws Exception;
//	
//	Trener create(Trener trener) throws Exception;
	
	Korisnik update(Korisnik korisnik) throws Exception;
	
	Korisnik login(String email, String lozinka);
	
	void delete(Long id);
}
