package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ftn.uns.ProbaProjekat.model.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long> {

	
	@Query("SELECT t FROM Termin t WHERE t.trening.trener.id = ?1")
	public List<Termin> findAllByTrener(Long id);
}
