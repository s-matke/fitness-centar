package ftn.uns.ProbaProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.model.Trening;
import ftn.uns.ProbaProjekat.model.dto.TreningDTO;
import ftn.uns.ProbaProjekat.service.TrenerService;
import ftn.uns.ProbaProjekat.service.TreningService;

@RestController
@RequestMapping(value = "/api/trening")
public class TreningController {
	
	private final TreningService treningService;
	private final TrenerService trenerService;
	
	@Autowired
	public TreningController(TreningService treningService, TrenerService trenerService) {
		this.treningService = treningService;
		this.trenerService = trenerService;
	}
	
	
	@GetMapping(
			value = "/lista",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TreningDTO>> getTrenings(@RequestParam(required=false) String keyword){
		
		//System.out.println("Keyword: " + keyword);
		
		List<TreningDTO> treningDTOS = new ArrayList<>();
		List<Trening> listaTreninga = this.treningService.findAll(keyword);  
		
		for (Trening trening : listaTreninga) {
			TreningDTO treningDTO = new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTip_treninga(), trening.getTrajanje());
			treningDTOS.add(treningDTO);
		}			
		
		
		return new ResponseEntity<>(treningDTOS, HttpStatus.OK);		
	}
	
	
	//Privremena pretraga po nazivu i trajanju	
	@GetMapping(
			value = "/lista/pretraga",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TreningDTO>> getTreningsCriterium(
			@RequestParam(required=false) String naziv,
			@RequestParam(required=false) String opis,
			@RequestParam(required=false) String tip,
			@RequestParam(required=false) Integer trajanje) 
	{
		List<TreningDTO> treningDTOS = new ArrayList<>();
		List<Trening> listaTreninga = null;
		
		//System.out.println("Naziv: " + naziv + "\nTrajanje: " + trajanje);
			
		if (!naziv.equalsIgnoreCase("")) {
			listaTreninga = this.treningService.findByNaziv(naziv);  
		}
		else if (!opis.equalsIgnoreCase("")) {
			listaTreninga = this.treningService.findByOpis(opis);
		}
		else if (!tip.equalsIgnoreCase("")) {
			listaTreninga = this.treningService.findByTip(tip);
		}
		else if (trajanje != null) {
			listaTreninga = this.treningService.findByTrajanje(trajanje);
		}
		
		for (Trening trening : listaTreninga) {
			TreningDTO treningDTO = new TreningDTO(trening.getId(), trening.getNaziv(), trening.getOpis(), trening.getTip_treninga(), trening.getTrajanje());
			treningDTOS.add(treningDTO);
		}				
		
		return new ResponseEntity<>(treningDTOS, HttpStatus.OK);	
		
	}
	
	// Pravljenje novog treninga
	@PostMapping (
			value = "/dodaj",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TreningDTO> createTrening(@RequestBody TreningDTO treningDTO) throws Exception {
		Trener trener = this.trenerService.findOne(treningDTO.getId());
		
		Trening trening = new Trening(treningDTO.getNaziv(), treningDTO.getOpis(), treningDTO.getTip_treninga(), treningDTO.getTrajanje(), trener);
		
		Trening noviTrening = this.treningService.create(trening);
		
		TreningDTO noviTreningDTO = new TreningDTO(noviTrening.getId(), noviTrening.getNaziv(), noviTrening.getOpis(), noviTrening.getTip_treninga(), noviTrening.getTrajanje());
		
		return new ResponseEntity<>(noviTreningDTO, HttpStatus.CREATED);
	}
	
		

}
