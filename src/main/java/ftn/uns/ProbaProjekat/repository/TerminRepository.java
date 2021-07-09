package ftn.uns.ProbaProjekat.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import ftn.uns.ProbaProjekat.model.Termin;

public interface TerminRepository extends JpaRepository<Termin, Long> {

	@Query("SELECT t FROM Termin t WHERE "
			+ "(LOWER(:naziv) IS NULL OR LOWER(t.trening.naziv) like LOWER(:naziv)) AND "
			+ "(LOWER(:tip_treninga) IS NULL OR LOWER(t.trening.tip) like LOWER(:tip_treninga)) AND"
			+ "(LOWER(:opis) IS NULL OR LOWER(t.trening.opis) like LOWER(:opis)) AND "
			+ "(:cenaOd IS NULL OR t.cena >= :cenaOd) AND "
			+ "(:cenaDo IS NULL OR t.cena <= :cenaDo) AND"
			+ "(:dateStart IS NULL OR t.pocetak BETWEEN :dateStart AND :dateEnd) ")
//			+ "(:datum IS NULL OR t.pocetak LIKE :datum) ")
	public List<Termin> search(
			@Param("naziv") String naziv,
			@Param("tip_treninga") String tip_treninga,
			@Param("opis") String opis, 
			@Param("cenaOd") Double cenaOd,
			@Param("cenaDo") Double cenaDo,
			@Param("dateStart") Timestamp dateStart,
			@Param("dateEnd") Timestamp dateEnd);
//			@Param("datum") Date datum);
	/*
	 * @Param("naziv") String naziv,
			@Param("tip_treninga") String tip_treninga,
			@Param("opis") String opis, 
			@Param("cena") Double cena, 
			@Param("datum") Date datum);
	 * 
	 * 
	 * 
	 * 
	 * 
	 */
	@Query("SELECT t FROM Termin t WHERE t.trening.trener.id = ?1")
	public List<Termin> findAllByTrener(Long id);
}
