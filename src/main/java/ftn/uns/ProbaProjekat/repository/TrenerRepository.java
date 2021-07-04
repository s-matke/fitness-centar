package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.uns.ProbaProjekat.model.Trener;

public interface TrenerRepository extends JpaRepository<Trener, Long> {
	
	List<Trener> findAllByFitnessCentar_Id(Long id);
}
