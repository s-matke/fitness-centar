package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ftn.uns.ProbaProjekat.model.PrijavaTermina;

public interface PrijavaTerminaRepository extends JpaRepository<PrijavaTermina, Long>{
	
	List<PrijavaTermina> findAll();
	
	List<PrijavaTermina> findAllByClan_id(Long id);
	
	@Query("SELECT t FROM PrijavaTermina t WHERE t.termin.trening.trener.id = ?1")
	public List<PrijavaTermina> search(Long id);
	
}
