package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.uns.ProbaProjekat.model.PrijavaTermina;

public interface PrijavaTerminaRepository extends JpaRepository<PrijavaTermina, Long>{
	
	List<PrijavaTermina> findAll();
	
	List<PrijavaTermina> findAllByClan_id(Long id);
}
