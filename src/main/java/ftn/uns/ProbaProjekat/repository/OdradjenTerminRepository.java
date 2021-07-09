package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ftn.uns.ProbaProjekat.model.OdradjenTermin;

public interface OdradjenTerminRepository extends JpaRepository<OdradjenTermin, Long> {

	
	@Query("SELECT t FROM OdradjenTermin t WHERE t.clan.id = ?1 AND t.ocena IS NOT NULL")
	public List<OdradjenTermin> findAllByGradedClan(Long id);
	
	@Query("SELECT t FROM OdradjenTermin t WHERE t.clan.id = ?1 AND t.ocena IS NULL")
	public List<OdradjenTermin> findAllByUngradedClan(Long id);
	
}
