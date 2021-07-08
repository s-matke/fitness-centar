package ftn.uns.ProbaProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.FitnessCentar;
import ftn.uns.ProbaProjekat.model.Sala;
import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.model.dto.SalaDTO;
import ftn.uns.ProbaProjekat.service.FitnessCentarService;
import ftn.uns.ProbaProjekat.service.SalaService;
import ftn.uns.ProbaProjekat.service.TrenerService;

@RestController
@RequestMapping(value="/api/sala")
public class SalaController {
	private final SalaService salaService;
	private final TrenerService trenerService;
	private final FitnessCentarService centarService;
	
	@Autowired
	public SalaController(SalaService salaService, TrenerService trenerService, FitnessCentarService centarService) {
		this.salaService = salaService;
		this.trenerService = trenerService;
		this.centarService = centarService;
	}
	
	@GetMapping(
			value = "/lista",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> listAllSala() {
		List<SalaDTO> salaDTOS = new ArrayList<>();
		List<Sala> listaSala = this.salaService.findAll();
		
		for (Sala sala : listaSala) {
			SalaDTO salaDTO = new SalaDTO (
					sala.getId(),
					sala.getOznaka(),
					sala.getKapacitet(),
					sala.getFitnessCentar().getId(),
					sala.getFitnessCentar().getNaziv());
			salaDTOS.add(salaDTO);
		}
		
		return new ResponseEntity<>(salaDTOS, HttpStatus.OK);
	}
	
	
	@GetMapping(
			value = "/lista/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> listSala(@PathVariable Long id) {
		List<SalaDTO> salaDTOS = new ArrayList<>();
		
		// ID - Trener id
		Trener trener = this.trenerService.findOne(id);
		
		for (Sala sala : trener.getFitnessCentar().getListaSala()) {
			SalaDTO salaDTO = new SalaDTO(sala.getId(), sala.getOznaka());
			salaDTOS.add(salaDTO);
		}
		
		return new ResponseEntity<>(salaDTOS, HttpStatus.OK);
	}
	
	@PutMapping( 
			value = "/izmeni/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> izmeniSalu(@PathVariable Long id, @RequestBody SalaDTO salaDTO) throws Exception {
		FitnessCentar centar = this.centarService.findOne(salaDTO.getCentar_id());
		if (centar == null) {
			throw new Exception("Centar sa datim ID-om ne postoji");
		}
		Sala sala = new Sala(id, salaDTO.getOznaka(), salaDTO.getKapacitet(), centar);
		
		Sala updatedSala = this.salaService.update(sala);
		System.out.println("Oznaka: " + updatedSala.getOznaka() + "\nKapacitet: " + updatedSala.getKapacitet() + "\nFit Gym: " + updatedSala.getFitnessCentar().getNaziv());
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/obrisi/{id}")
	public ResponseEntity<Void> obrisiSalu(@PathVariable Long id) throws Exception {
		Sala sala = this.salaService.findOne(id);
		
		if (sala == null) {
			throw new Exception("Sala sa datim ID-om ne postoji");
		}
		
		this.salaService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@PostMapping(
			value = "/add",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> createSala(@RequestBody SalaDTO salaDTO) throws Exception {
		FitnessCentar centar = this.centarService.findOne(salaDTO.getCentar_id());
		Sala sala = new Sala(salaDTO.getOznaka(), salaDTO.getKapacitet(), centar);
		
		Sala novaSala = this.salaService.create(sala);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
