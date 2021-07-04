package ftn.uns.ProbaProjekat.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import ftn.uns.ProbaProjekat.model.Sala;

public interface SalaRepository extends JpaRepository<Sala, Long> {

	List<Sala> findAllByFitnessCentar_id(Long id);
}
