package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.uns.ProbaProjekat.model.Trening;

public interface TreningRepository extends JpaRepository<Trening, Long> {
	
	List<Trening> findAll();
}
