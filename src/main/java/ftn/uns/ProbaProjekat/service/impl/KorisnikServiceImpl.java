package ftn.uns.ProbaProjekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.Korisnik;
import ftn.uns.ProbaProjekat.repository.KorisnikRepository;
import ftn.uns.ProbaProjekat.service.KorisnikService;

@Service
public class KorisnikServiceImpl implements KorisnikService {
	
	public final KorisnikRepository korisnickiRepozitorijum;
	
	@Autowired
	public KorisnikServiceImpl(KorisnikRepository korisnickiRepositorijum) {
		this.korisnickiRepozitorijum = korisnickiRepositorijum;
	}

	@Override
	public Korisnik findOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Korisnik> findAll() {
		List<Korisnik> korisnici = this.korisnickiRepozitorijum.findAll();
		return korisnici;
	}

	@Override
	public Korisnik create(Korisnik korisnik) throws Exception {
		
		if (korisnik.getId() != null) {
			throw new Exception("ID must be null!");
		}
		Korisnik noviKorisnik = this.korisnickiRepozitorijum.save(korisnik);
		return noviKorisnik;
	}

	@Override
	public Korisnik update(Korisnik korisnik) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Korisnik login(String email, String lozinka) {
		return this.korisnickiRepozitorijum.findByEmailAndLozinka(email, lozinka);
	}

	@Override
	public void delete(Long id) {
		
	}

}
