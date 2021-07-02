package ftn.uns.ProbaProjekat.controller;

import java.sql.Timestamp;
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

import ftn.uns.ProbaProjekat.model.Sala;
import ftn.uns.ProbaProjekat.model.Termin;
import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.model.Trening;
import ftn.uns.ProbaProjekat.model.dto.TerminDTO;
import ftn.uns.ProbaProjekat.model.dto.TrenerDTO;
import ftn.uns.ProbaProjekat.model.dto.TreningDTO;
import ftn.uns.ProbaProjekat.service.SalaService;
import ftn.uns.ProbaProjekat.service.TerminService;
import ftn.uns.ProbaProjekat.service.TrenerService;
import ftn.uns.ProbaProjekat.service.TreningService;


@RestController
@RequestMapping(value = "/api/trener")
public class TrenerController {
	private final TrenerService trenerService;
	private final TreningService treningService;
	private final SalaService salaService;
	private final TerminService terminService;
	
	@Autowired
	public TrenerController(TrenerService trenerService, TreningService treningService, SalaService salaService, TerminService terminService) {
		this.trenerService = trenerService;
		this.treningService = treningService;
		this.salaService = salaService;
		this.terminService = terminService;
	}
	
	@PostMapping(
			value="/register",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrenerDTO> createTrener(@RequestBody TrenerDTO trenerDTO) throws Exception {
		
		trenerDTO.setStatus(false);	// ostaje na Administratoru da dozvoli/odbije zahtev za pristup sistemu (samo za trenere)
		
		Trener trener = new Trener(trenerDTO.getUserName(), trenerDTO.getLozinka(), trenerDTO.getIme(), trenerDTO.getPrezime(), trenerDTO.getEmail(),
				trenerDTO.getTelefon(), trenerDTO.getDate(), trenerDTO.getRole(), trenerDTO.getTip_korisnika(), trenerDTO.getStatus(), 0.0);
		
		Trener noviTrener = trenerService.create(trener);
		
		TrenerDTO noviTrenerDTO = new TrenerDTO(noviTrener.getId(), noviTrener.getUserName(), noviTrener.getLozinka(), noviTrener.getIme(), noviTrener.getPrezime(), noviTrener.getEmail(),
				noviTrener.getTelefon(), noviTrener.getDate(), noviTrener.getRole(), noviTrener.getTip_korisnika(), noviTrener.getStatus(), noviTrener.getAvgOcena());
		
		return new ResponseEntity<>(noviTrenerDTO, HttpStatus.CREATED);
	}
	
	// Lista trenera (potrebna za odobravanje pristupa sistemu)
	@GetMapping(
			value = "/treneri",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TrenerDTO>> getTrener() {
		List<Trener> listaTrenera = this.trenerService.findAll();
		
		List<TrenerDTO> trenerDTOS = new ArrayList<>();
		
		for (Trener trener : listaTrenera) {
			//if (trener.getStatus() == false) 
			{
				TrenerDTO trenerDTO = new TrenerDTO(trener.getId(), trener.getIme(), trener.getPrezime(), trener.getStatus());
				trenerDTOS.add(trenerDTO);
			}
		}
		
		return new ResponseEntity<>(trenerDTOS, HttpStatus.OK);
	}
	
	// Lista treninga koje drzi trener
	@GetMapping(
			value = "/trening/lista",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TreningDTO>> listTrening(@RequestParam(required=true) Long id) throws Exception {
		List<TreningDTO> treningDTOS = new ArrayList<>();
		List<Trening> listaTreninga = this.treningService.findByTrener(id);
		
		for (Trening trening : listaTreninga) {
			TreningDTO treningDTO = new TreningDTO(trening.getId(), trening.getNaziv(), trening.getTip_treninga(), trening.getOpis(), trening.getTrajanje());
			treningDTOS.add(treningDTO);
		}
		
		return new ResponseEntity<>(treningDTOS, HttpStatus.OK);
	}
	
	@PostMapping( 
			value = "/termin/dodaj",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TerminDTO> addTermin(@RequestBody TerminDTO terminDTO) throws Exception {
		// Provera validnosti datuma prosledjenog za termin
		Timestamp now = new Timestamp(System.currentTimeMillis());
		if ((terminDTO.getEpoha() * 1000l) < now.getTime()) {
			throw new Exception("Datum mora biti validan, odnosno novi termin mora biti odrzan u buducnosti.");
		}
		
		Trening trening = this.treningService.findOne(terminDTO.getTrening_id());
		
		terminDTO.setPocetak(new Timestamp(terminDTO.getEpoha() * 1000l));
		
		Long id = (long) 1;	// TMP resenje dok ne dodam funkcionalnost za izbor sale
		Sala sala = this.salaService.findOne(id);
		
		Termin termin = new Termin(terminDTO.getPocetak(), terminDTO.getCena(), sala, trening);
		Termin noviTermin = terminService.create(termin);
		
		TerminDTO noviTerminDTO = new TerminDTO(noviTermin.getId(), noviTermin.getPocetak(), noviTermin.getCena(), noviTermin.getSala().getId(), noviTermin.getTrening().getId());
		
		return new ResponseEntity<>(noviTerminDTO, HttpStatus.CREATED);
	}
	
}













