package ftn.uns.ProbaProjekat.controller;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.Clan;
import ftn.uns.ProbaProjekat.model.PrijavaTermina;
import ftn.uns.ProbaProjekat.model.Sala;
import ftn.uns.ProbaProjekat.model.Termin;
import ftn.uns.ProbaProjekat.model.Trening;
import ftn.uns.ProbaProjekat.model.dto.PrijavaDTO;
import ftn.uns.ProbaProjekat.model.dto.PrijavaTerminaDTO;
import ftn.uns.ProbaProjekat.model.dto.TerminDTO;
import ftn.uns.ProbaProjekat.model.dto.TermingDTO;
import ftn.uns.ProbaProjekat.service.ClanService;
import ftn.uns.ProbaProjekat.service.PrijavaTerminaService;
import ftn.uns.ProbaProjekat.service.SalaService;
import ftn.uns.ProbaProjekat.service.TerminService;
import ftn.uns.ProbaProjekat.service.TrenerService;
import ftn.uns.ProbaProjekat.service.TreningService;

@RestController
@RequestMapping(value = "/api/termin")
public class TerminController {
	
	private final TerminService terminService;
	private final ClanService clanService;
	private final PrijavaTerminaService prijavaService;
	private final TrenerService trenerService;
	private final TreningService treningService;
	private final SalaService salaService;
	
	@Autowired
	public TerminController(TerminService terminService, ClanService clanService, PrijavaTerminaService prijavaService, TrenerService trenerService, TreningService treningService, SalaService salaService) {
		this.terminService = terminService;
		this.clanService = clanService;
		this.prijavaService = prijavaService;
		this.trenerService = trenerService;
		this.treningService = treningService;
		this.salaService = salaService;
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
					termin.getTrening().getTrener().getFitnessCentar().getNaziv(),
					termin.getSala().getOznaka(),
					termin.getId());
			
			// Provera da li je clan vec prijavljen na neki od termina
			if (id != null) {
				for (PrijavaTermina prijava : listaPrijavljenihTermina) {
					if (prijava.getTermin().getId() == termin.getId()) {
						preskoci = true;
					}
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
	
	// Lista prijavljenih termina
	@GetMapping( 
			value = "/clan/prijavljeni",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TermingDTO>> viewTermin(@RequestParam(required=true) Long clan_id) throws Exception {
		List<TermingDTO> prijavaDTOS = new ArrayList<>();
		List<PrijavaTermina> listaTermina = this.prijavaService.findByClan(clan_id);
		
		SimpleDateFormat crunchifyDate = new SimpleDateFormat("MMM dd yyyy");
		SimpleDateFormat crunchifyTime = new SimpleDateFormat("HH:mm");
		
		String datum, vreme;
		
		for (PrijavaTermina termin : listaTermina) {
			datum = crunchifyDate.format(termin.getTermin().getPocetak());
			vreme = crunchifyTime.format(termin.getTermin().getPocetak());
			
			if (termin.getTermin().getTrening().getTrener().getStatus() == false) {
				continue;
			}
			
			TermingDTO termingDTO = new TermingDTO(
					termin.getTermin().getTrening().getNaziv(),
					termin.getTermin().getTrening().getOpis(),
					termin.getTermin().getCena(),
					termin.getTermin().getSala().getOznaka(),
					datum,
					vreme,
					termin.getTermin().getTrening().getTrener().getIme() + " " + termin.getTermin().getTrening().getTrener().getPrezime(),
					termin.getId());
			prijavaDTOS.add(termingDTO);
		}
		
		return new ResponseEntity<>(prijavaDTOS, HttpStatus.OK);
	}
	

	// Lista nadolazecih termina za trenera
	@GetMapping(
			value = "/trener/prijavljeni",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<PrijavaTerminaDTO>> getUpcomingSessions(@RequestParam(required = true) Long trener_id) throws Exception {
		// Potrebne povratne informacije: Clan ime+prezime, Vreme+Datum+Cena Termina i Oznaka sale u kojoj se odrzava termin
		//Trener trener = this.trenerService.findOne(trener_id);
		List<PrijavaTerminaDTO> prijavaDTOS = new ArrayList<>(); 
		List<PrijavaTermina> listaTermina = this.prijavaService.findAllById(trener_id);
		
		SimpleDateFormat crunchifyDate = new SimpleDateFormat("MMM dd yyyy");
		SimpleDateFormat crunchifyTime = new SimpleDateFormat("HH:mm");
		
		String datum, vreme;
		
		for (PrijavaTermina prijava : listaTermina) {
			vreme = crunchifyTime.format(prijava.getTermin().getPocetak());
			datum = crunchifyDate.format(prijava.getTermin().getPocetak());
			PrijavaTerminaDTO prijavaDTO = new PrijavaTerminaDTO(
					prijava.getClan().getIme() + " " + prijava.getClan().getPrezime(),
					prijava.getTermin().getCena(),
					vreme,
					datum,
					prijava.getTermin().getSala().getOznaka());

			prijavaDTOS.add(prijavaDTO);
		}
		
		return new ResponseEntity<>(prijavaDTOS, HttpStatus.OK);
	}
		
	// Prijavljivanje termina
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
		
		// Provera da li ima mesta u sali
		//termin.getSala().getKapacitet()
		//if (this.terminService.getPrijavljeni(termin.getPocetak()))
		
//		System.out.println("Vreme:" + termin.getPocetak().getTime());
//		System.out.println("Timestamp Pocetak:" + termin.getPocetak());
		long epoha = termin.getPocetak().getTime() + ((long)termin.getTrening().getTrajanje() * 3600000l);
		Timestamp novo =  new Timestamp(epoha);
//		System.out.println("Timestamp sabran?:" + novo);
		int mesta = this.prijavaService.slobodnaMesta(termin.getPocetak(), novo);	// saljemo epohu kada se planira izvoditi termin + vreme trajanja
		int slobodnaMesta = termin.getSala().getKapacitet() - mesta;
		
		if (slobodnaMesta <= 0) {
			System.out.println("Nema slobodnog mesta za prijavu termina");
			return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
		}
		
		System.out.println("Broj prijavljenih: " + mesta);
		System.out.println();
		
		PrijavaTermina prijava = new PrijavaTermina(clan, termin);
		
		PrijavaTermina novaPrijava = prijavaService.create(prijava);
		
		PrijavaTerminaDTO novaPrijavaDTO = new PrijavaTerminaDTO(novaPrijava.getId(), novaPrijava.getClan().getId(), novaPrijava.getTermin().getId());
		
		return new ResponseEntity<>(novaPrijavaDTO, HttpStatus.CREATED);
	}
	
	// Odjava termina
	@DeleteMapping(value = "/odjava")
	public ResponseEntity<Long> odjavaTermina(@RequestParam(required=true) Long id) throws Exception {
		
		if (this.prijavaService.findOne(id) == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		this.prijavaService.delete(id);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@GetMapping(
			value = "/lista/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TerminDTO>> listTermins(@PathVariable Long id) {
		List<TerminDTO> terminDTOS = new ArrayList<>();
		List<Termin> listaTermina = this.terminService.findAllByTrener(id);
		
		SimpleDateFormat crunchifyDate = new SimpleDateFormat("MMM dd yyyy");
		SimpleDateFormat crunchifyTime = new SimpleDateFormat("HH:mm");

		String datum, vreme;
		long epoha;
		long epoha_kraj;
		
		for (Termin termin : listaTermina) {
			datum = crunchifyDate.format(termin.getPocetak());
			vreme = crunchifyTime.format(termin.getPocetak());
			epoha = termin.getPocetak().getTime();
			epoha_kraj = termin.getKraj().getTime();
			TerminDTO terminDTO = new TerminDTO(
					termin.getId(),
					termin.getTrening().getNaziv(),
					termin.getTrening().getTrajanje(),
					termin.getCena(),
					vreme,
					datum,
					termin.getSala().getId(),
					termin.getSala().getOznaka());
			terminDTOS.add(terminDTO);
		}
		
		return new ResponseEntity<>(terminDTOS, HttpStatus.OK);
	}
	
	@PutMapping(
			value = "/izmeni/{id}",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Void> izmeniTermin(@PathVariable Long id, @RequestBody TerminDTO terminDTO) throws Exception {
		Termin termin = this.terminService.findOne(id);
		//System.out.println("ID: " + id);
		Trening trening = this.treningService.findOne(termin.getTrening().getId());
		
		Sala sala = this.salaService.findOne(terminDTO.getTrening_id());
		
		Termin terminT;
//		
		if (termin == null || trening == null || sala == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		System.out.println("EPOHA: " + terminDTO.getEpoha());
		//long epoha = terminDTO.getEpoha();
		if (terminDTO.getEpoha() != 0l) {
			System.out.println("Epoha nije nula");
			Timestamp novPocetak= new Timestamp(terminDTO.getEpoha() * 1000l);
			Timestamp novKraj = new Timestamp(terminDTO.getEpoha() + ((long)trening.getTrajanje() * 3600000l));
			//	public Termin(Long id, Timestamp pocetak, Timestamp kraj, Double cena, Sala sala, Trening trening) {
			terminT = new Termin(id, novPocetak, novKraj, terminDTO.getCena(), sala, trening);
		} else {
			terminT = new Termin(id, termin.getPocetak(), termin.getKraj(), terminDTO.getCena(), sala, trening);
		}
		Termin updatedTermin = this.terminService.update(terminT);
		
		return new ResponseEntity<>(HttpStatus.OK);
		//Termin = new Termin(id, terminDTO.getCena())
	}
	
	@DeleteMapping(value="/obrisi/{id}")
	public ResponseEntity<Void> obrisiTermin(@PathVariable Long id) throws Exception {
		Termin termin = this.terminService.findOne(id);
		
		if (termin == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		this.terminService.delete(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
