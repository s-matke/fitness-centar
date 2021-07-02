package ftn.uns.ProbaProjekat.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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

import ftn.uns.ProbaProjekat.model.Clan;
import ftn.uns.ProbaProjekat.model.PrijavaTermina;
import ftn.uns.ProbaProjekat.model.Termin;
import ftn.uns.ProbaProjekat.model.dto.PrijavaDTO;
import ftn.uns.ProbaProjekat.model.dto.PrijavaTerminaDTO;
import ftn.uns.ProbaProjekat.model.dto.TermingDTO;
import ftn.uns.ProbaProjekat.service.ClanService;
import ftn.uns.ProbaProjekat.service.PrijavaTerminaService;
import ftn.uns.ProbaProjekat.service.TerminService;

@RestController
@RequestMapping(value = "/api/termin")
public class TerminController {
	
	private final TerminService terminService;
	private final ClanService clanService;
	private final PrijavaTerminaService prijavaService;
	
	@Autowired
	public TerminController(TerminService terminService, ClanService clanService, PrijavaTerminaService prijavaService) {
		this.terminService = terminService;
		this.clanService = clanService;
		this.prijavaService = prijavaService;		
	}
	
	// Izlistavanje termina koji se mogu prijaviti
	@GetMapping(
			value = "/lista",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TermingDTO>> getTerming(@RequestParam(required=false) Long id) {
		List<TermingDTO> termingDTOS = new ArrayList<>();
		List<Termin> listaTermina = this.terminService.findAll();
		List<PrijavaTermina> listaPrijavljenihTermina = this.prijavaService.findByClan(id);
		
		// Jednostavniji ispis datum-a i vremen-a korisniku
		SimpleDateFormat crunchifyDate = new SimpleDateFormat("MMM dd yyyy");
		SimpleDateFormat crunchifyTime = new SimpleDateFormat("HH:mm");
		
		// trenutno vreme u formatu timestamp-a
		Timestamp now = new Timestamp(System.currentTimeMillis());
		
		boolean preskoci = false;
		String datum;
		String vreme;
		
		for (Termin termin : listaTermina) {
			// Proveravamo da li je nekom od trenera status promenjen na false, cime im se zabranjuje pristu sajtu (TODO: obrisati termine prilikom promene statusa?)
			// takodje, proveravamo da li je neki od termina zavrsen "vremenski
			if (termin.getTrening().getTrener().getStatus() == false || termin.getPocetak().getTime() < now.getTime()) {
				continue;
			}
			
			datum = crunchifyDate.format(termin.getPocetak());
			vreme = crunchifyTime.format(termin.getPocetak());
			
			TermingDTO termingDTO = new TermingDTO(
					termin.getTrening().getNaziv(),
					termin.getTrening().getOpis(),
					termin.getTrening().getTip_treninga(),
					termin.getTrening().getTrajanje(),
					datum,
					vreme,
					termin.getCena(),
					termin.getTrening().getTrener().getIme() + " " + termin.getTrening().getTrener().getPrezime(),
					termin.getSala().getOznaka(),
					termin.getId());
			
			// Provera da li je clan vec prijavljen na neki od termina
			for (PrijavaTermina prijava : listaPrijavljenihTermina) {
				if (prijava.getTermin().getId() == termin.getId()) {
					preskoci = true;
				}
			}
			
			if (preskoci == false) {
				termingDTOS.add(termingDTO);
			}
			preskoci = false;
		}
		
		if (termingDTOS.size() == 0) {
			return new ResponseEntity<>(termingDTOS, HttpStatus.BAD_REQUEST);
		}
		
		return new ResponseEntity<>(termingDTOS, HttpStatus.OK);
		
	}
	
	
	// Prijavljivanje na termin
	@PostMapping( 
			value = "/prijava", 
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<PrijavaTerminaDTO> createPrijava(@RequestBody PrijavaDTO prijavaDTO) throws Exception {
		
		if (prijavaDTO.getClan_id() == null || prijavaDTO.getTermin_id() == null) {
			System.out.println("Korisnik nije ulogovan ili nije izabrao termin");
			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
		}
		
		Clan clan = this.clanService.findOne(prijavaDTO.getClan_id());	// pronalazimo clana sa datim ID-om
		Termin termin = this.terminService.findOne(prijavaDTO.getTermin_id());	// pronalazimo termin sa datim ID-om
		
		PrijavaTermina prijava = new PrijavaTermina(clan, termin);
		
		PrijavaTermina novaPrijava = prijavaService.create(prijava);
		
		PrijavaTerminaDTO novaPrijavaDTO = new PrijavaTerminaDTO(novaPrijava.getId(), novaPrijava.getClan().getId(), novaPrijava.getTermin().getId());
		
		return new ResponseEntity<>(novaPrijavaDTO, HttpStatus.CREATED);
	}

}
