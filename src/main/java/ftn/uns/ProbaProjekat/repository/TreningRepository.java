package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ftn.uns.ProbaProjekat.model.Trening;

public interface TreningRepository extends JpaRepository<Trening, Long> {
	
	List<Trening> findAll();
	
	@Query("SELECT t FROM Trening t WHERE LOWER(t.naziv) LIKE %?1%"
			+ " OR LOWER(t.opis) LIKE %?1%"
			+ " OR LOWER(t.tip) LIKE %?1%"
			+ " OR CONCAT(t.trajanje, '') LIKE %?1%"
			+ " ORDER BY t.naziv ASC")
	public List<Trening> search(String keyword);
	
	List<Trening> findByNazivOrderByNaziv(String naziv);
	
	List<Trening> findByTrajanjeOrderByTrajanje(Integer trajanje);
	
	List<Trening> findByOpisOrderByOpis(String opis);
	
	List<Trening> findByTipOrderByTip(String tip);
}
