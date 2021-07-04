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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.FitnessCentar;
import ftn.uns.ProbaProjekat.model.dto.FitnessCentarDTO;
import ftn.uns.ProbaProjekat.service.FitnessCentarService;
import ftn.uns.ProbaProjekat.service.SalaService;
import ftn.uns.ProbaProjekat.service.TrenerService;

@RestController
@RequestMapping(value="/api/centar")
public class FitnessCentarController {
	
	private final FitnessCentarService centarService;
	private final TrenerService trenerService;
	private final SalaService salaService;
	
	@Autowired
	public FitnessCentarController(FitnessCentarService centarService, TrenerService trenerService, SalaService salaService) {
		this.centarService = centarService;
		this.trenerService = trenerService;
		this.salaService = salaService;
	}
	
	@GetMapping(
			value = "/opcije",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FitnessCentarDTO>> listCentar() {
		List<FitnessCentarDTO> centarDTOS = new ArrayList<>();
		List<FitnessCentar> listaCentara = this.centarService.findAll();
		
		for (FitnessCentar centar : listaCentara) {
			FitnessCentarDTO centarDTO = new FitnessCentarDTO(centar.getId(), centar.getNaziv());
			centarDTOS.add(centarDTO);
		}
		
		return new ResponseEntity<>(centarDTOS, HttpStatus.OK);
	}

	@GetMapping(
			value = "/lista", 
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FitnessCentarDTO>> viewCentar() {
		List<FitnessCentarDTO> centarDTOS = new ArrayList<>();
		List<FitnessCentar> listaCentara = this.centarService.findAll();
		
		Integer sale;
		Integer treneri;
		for (FitnessCentar centar : listaCentara) {
			treneri = this.trenerService.findByCentar(centar.getId()).size();
			sale = this.salaService.findByCentar(centar.getId()).size();
			FitnessCentarDTO centarDTO = new FitnessCentarDTO(
					centar.getId(),
					centar.getNaziv(),
					centar.getAdresa(),
					sale,
					treneri
					);
			System.out.println("Treneri: " + treneri + "\nSIZE: " + this.trenerService.findByCentar(centar.getId()).size());
			centarDTOS.add(centarDTO);
		}
		
		return new ResponseEntity<>(centarDTOS, HttpStatus.OK);
	}
	
	@DeleteMapping(value = "/obrisi/{id}")
	public ResponseEntity<Void> obrisiCentar(@PathVariable Long id) throws Exception {
		FitnessCentar centar = this.centarService.findOne(id);
		if (centar == null) {
			throw new Exception("Centar, sa datim ID-om, ne postoji u bazi.");
		}
		
		this.centarService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
}
