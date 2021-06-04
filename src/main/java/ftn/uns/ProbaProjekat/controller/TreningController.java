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

import ftn.uns.ProbaProjekat.model.Trening;
import ftn.uns.ProbaProjekat.model.dto.TreningDTO;
import ftn.uns.ProbaProjekat.service.TreningService;

@RestController
@RequestMapping(value = "/api/trening")
public class TreningController {
	
	private final TreningService treningService;
	
	@Autowired
	public TreningController(TreningService treningService) {
		this.treningService = treningService;
	}
	
	
	@GetMapping(
			value = "/lista",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TreningDTO>> getTrenings() {
		
		List<Trening> listaTreninga = this.treningService.findAll();
		List<TreningDTO> treningDTOS = new ArrayList<>();
		
		for (Trening trening : listaTreninga) {
			TreningDTO treningDTO = new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTip_treninga(), trening.getTrajanje());
			treningDTOS.add(treningDTO);
		}
		
		return new ResponseEntity<>(treningDTOS, HttpStatus.OK);		
	}
	

}
