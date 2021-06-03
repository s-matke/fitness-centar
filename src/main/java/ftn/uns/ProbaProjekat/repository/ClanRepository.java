package ftn.uns.ProbaProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.uns.ProbaProjekat.model.Clan;
import ftn.uns.ProbaProjekat.model.Korisnik;

public interface ClanRepository extends JpaRepository<Clan, Long>{
	
	Korisnik findByEmailAndLozinka(String email, String lozinka);
	
//	Clan findByUserName(String userName);
//	Clan findByEmail(String email);
//	Clan findByTelefon(String telefon);
}
