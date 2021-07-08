package ftn.uns.ProbaProjekat.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ftn.uns.ProbaProjekat.model.PrijavaTermina;

public interface PrijavaTerminaRepository extends JpaRepository<PrijavaTermina, Long>{
	
	List<PrijavaTermina> findAll();
	
	List<PrijavaTermina> findAllByClan_id(Long id);
	
	@Query("SELECT t FROM PrijavaTermina t WHERE t.termin.trening.trener.id = ?1")
	public List<PrijavaTermina> search(Long id);
	
//	@Query("SELECT t FROM PrijavaTermina t WHERE t.termin.pocetak BETWEEN ?1 AND ?2"
//			+ " OR t.termin.kraj BETWEEN ?1 AND ?2")
//	public List<PrijavaTermina> mesta(Timestamp pocetak, Timestamp kraj);
	
	
	@Query("SELECT t FROM PrijavaTermina t WHERE t.termin.pocetak > ?1 AND t.termin.pocetak < ?2"
			+ " OR t.termin.kraj > ?1 AND t.termin.kraj < ?2")
	public List<PrijavaTermina> novomesto(Timestamp pocetak, Timestamp kraj);
}
