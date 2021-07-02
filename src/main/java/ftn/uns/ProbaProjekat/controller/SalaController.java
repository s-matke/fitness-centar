package ftn.uns.ProbaProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.Sala;
import ftn.uns.ProbaProjekat.model.dto.SalaDTO;
import ftn.uns.ProbaProjekat.service.SalaService;

@RestController
@RequestMapping(value="/api/sala")
public class SalaController {
	private final SalaService salaService;
	
	@Autowired
	public SalaController(SalaService salaService) {
		this.salaService = salaService;
	}
	
	@GetMapping(
			value = "/lista",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<SalaDTO>> listSala() {
		List<SalaDTO> salaDTOS = new ArrayList<>();
		List<Sala> listaSala = this.salaService.findAll();
		
		for (Sala sala : listaSala) {
			SalaDTO salaDTO = new SalaDTO(sala.getId(), sala.getOznaka());
			salaDTOS.add(salaDTO);
		}
		
		return new ResponseEntity<>(salaDTOS, HttpStatus.OK);
	}
	
}
