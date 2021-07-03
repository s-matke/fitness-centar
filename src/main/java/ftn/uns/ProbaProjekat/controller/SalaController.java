package ftn.uns.ProbaProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.Sala;
import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.model.dto.SalaDTO;
import ftn.uns.ProbaProjekat.service.SalaService;
import ftn.uns.ProbaProjekat.service.TrenerService;

@RestController
@RequestMapping(value="/api/sala")
public class SalaController {
	private final SalaService salaService;
	private final TrenerService trenerService;
	
	@Autowired
	public SalaController(SalaService salaService, TrenerService trenerService) {
		this.salaService = salaService;
		this.trenerService = trenerService;
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
	
}
