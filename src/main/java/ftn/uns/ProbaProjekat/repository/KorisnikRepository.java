package ftn.uns.ProbaProjekat.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.uns.ProbaProjekat.model.Korisnik;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
	Korisnik findByEmailAndLozinka(String email, String lozinka);
}
