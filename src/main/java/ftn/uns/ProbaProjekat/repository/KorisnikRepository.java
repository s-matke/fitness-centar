package ftn.uns.ProbaProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ftn.uns.ProbaProjekat.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	
	@Query("SELECT k FROM Korisnik k WHERE LOWER(k.email) = ?1" 
			+ " AND k.lozinka = ?2")
	Korisnik findByEmailAndLozinkaIgnoreCase(String email, String lozinka);
}
