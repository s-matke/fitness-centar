package ftn.uns.ProbaProjekat.controller;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.FitnessCentar;
import ftn.uns.ProbaProjekat.model.Sala;
import ftn.uns.ProbaProjekat.model.Termin;
import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.model.Trening;
import ftn.uns.ProbaProjekat.model.dto.TerminDTO;
import ftn.uns.ProbaProjekat.model.dto.TrenerDTO;
import ftn.uns.ProbaProjekat.model.dto.TreningDTO;
import ftn.uns.ProbaProjekat.service.FitnessCentarService;
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
	private final FitnessCentarService centarService;
	
	@Autowired
	public TrenerController(TrenerService trenerService, TreningService treningService, SalaService salaService, TerminService terminService, FitnessCentarService centarService) {
		this.trenerService = trenerService;
		this.treningService = treningService;
		this.salaService = salaService;
		this.terminService = terminService;
		this.centarService = centarService;
	}
	
	@PostMapping(
			value="/register",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrenerDTO> createTrener(@RequestBody TrenerDTO trenerDTO) throws Exception {
		
		trenerDTO.setStatus(false);	// ostaje na Administratoru da dozvoli/odbije zahtev za pristup sistemu (samo za trenere)
		
		//System.out.println("ID Centra: " + trenerDTO.getFitnessCentar_id());
		
		FitnessCentar centar = this.centarService.findOne(trenerDTO.getFitnessCentar_id());
		
		Trener trener = new Trener(trenerDTO.getUserName(), trenerDTO.getLozinka(), trenerDTO.getIme(), trenerDTO.getPrezime(), trenerDTO.getEmail(),
				trenerDTO.getTelefon(), trenerDTO.getDate(), trenerDTO.getRole(), trenerDTO.getTip_korisnika(), trenerDTO.getStatus(), 0.0, centar);
		
		Trener noviTrener = trenerService.create(trener);
		
		TrenerDTO noviTrenerDTO = new TrenerDTO(noviTrener.getId(), noviTrener.getUserName(), noviTrener.getLozinka(), noviTrener.getIme(), noviTrener.getPrezime(), noviTrener.getEmail(),
				noviTrener.getTelefon(), noviTrener.getDate(), noviTrener.getRole(), noviTrener.getTip_korisnika(), noviTrener.getStatus(), noviTrener.getAvgOcena());
		
		return new ResponseEntity<>(noviTrenerDTO, HttpStatus.CREATED);
	}
	
	@PostMapping(
			value="/admin-register",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrenerDTO> createTrenerTrue(@RequestBody TrenerDTO trenerDTO) throws Exception {
		
		trenerDTO.setStatus(true);	// ostaje na Administratoru da dozvoli/odbije zahtev za pristup sistemu (samo za trenere)
		
		//System.out.println("ID Centra: " + trenerDTO.getFitnessCentar_id());
		
		FitnessCentar centar = this.centarService.findOne(trenerDTO.getFitnessCentar_id());
		
		Trener trener = new Trener(trenerDTO.getUserName(), trenerDTO.getLozinka(), trenerDTO.getIme(), trenerDTO.getPrezime(), trenerDTO.getEmail(),
				trenerDTO.getTelefon(), trenerDTO.getDate(), trenerDTO.getRole(), trenerDTO.getTip_korisnika(), trenerDTO.getStatus(), 0.0, centar);
		
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
				Set<Trening> treninzi = trener.getListaTreninga();
				for (Trening trening : treninzi) {
					System.out.println(trening);
				}
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
		System.out.println("Now:" + now + "\nEpoch:" + terminDTO.getEpoha() + "\nEpoch*1000:" + terminDTO.getEpoha() * 1000l + "\nNowTime: " + now.getTime());
		if ((terminDTO.getEpoha() * 1000l) < now.getTime()) {
			throw new Exception("Datum mora biti validan, odnosno novi termin mora biti odrzan u buducnosti.");
		}
		
		if (terminDTO.getSala_id() == null) {
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		Trening trening = this.treningService.findOne(terminDTO.getTrening_id());
		
		terminDTO.setPocetak(new Timestamp(terminDTO.getEpoha() * 1000l));
		
		long epoha = terminDTO.getPocetak().getTime() + ((long)trening.getTrajanje() * 3600000l);	// kad se zavrsava termin
		Timestamp end =  new Timestamp(epoha);
		
		Sala sala = this.salaService.findOne(terminDTO.getSala_id());
			
		Termin termin = new Termin(terminDTO.getPocetak(), end, terminDTO.getCena(), sala, trening);
		Termin noviTermin = terminService.create(termin);
		
		TerminDTO noviTerminDTO = new TerminDTO(noviTermin.getId(), noviTermin.getPocetak(), noviTermin.getCena(), noviTermin.getSala().getId(), noviTermin.getTrening().getId());
		
		return new ResponseEntity<>(noviTerminDTO, HttpStatus.CREATED);
	}
	
	@DeleteMapping(value="/obrisi")
	public ResponseEntity<Void> obrisiTrenera(@RequestBody Long[] array) throws Exception {
		//System.out.println("Uso u delete");
		for (int i = 0; i < array.length; ++i) {
			Long tmpId = array[i];
			Trener trener = this.trenerService.findOne(tmpId);
//			System.out.println("Trener: " + trener.getIme() + " " + trener.getPrezime());
			if (trener == null) {
				throw new Exception("Trener, sa datim ID-om, ne postoji!");
			}
			this.trenerService.delete(tmpId);
		}
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	@GetMapping(
			value = "/profil/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<TrenerDTO> viewProfile(@PathVariable Long id) {
		Trener trener = this.trenerService.findOne(id);
		
		TrenerDTO trenerDTO = new TrenerDTO(
				trener.getUserName(),
				trener.getIme() + " " + trener.getPrezime(),
				trener.getEmail(),
				trener.getTelefon(),
				trener.getRole(),
				trener.getAvgOcena());
		
		return new ResponseEntity<>(trenerDTO, HttpStatus.OK);
		
	}
	
}













