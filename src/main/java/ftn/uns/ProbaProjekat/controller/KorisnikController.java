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
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.Korisnik;
import ftn.uns.ProbaProjekat.model.dto.KorisnikDTO;
import ftn.uns.ProbaProjekat.model.dto.LoginKorisnikDTO;
import ftn.uns.ProbaProjekat.service.KorisnikService;

@RestController
@RequestMapping(value = "/api/korisnik")
public class KorisnikController {
	
	private final KorisnikService korisnickiService;
	
	@Autowired
	public KorisnikController(KorisnikService korisnickiService) {
		this.korisnickiService = korisnickiService;
	}
	
	@GetMapping(
			value = "/korisnici",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<KorisnikDTO>> getKorisnici() {
		List<Korisnik> listaKorisnika = this.korisnickiService.findAll();
		
		List<KorisnikDTO> korisnikDTOS = new ArrayList<>();
		
		for (Korisnik korisnik : listaKorisnika) {
			KorisnikDTO korisnikDTO = new KorisnikDTO(korisnik.getId(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getStatus(), korisnik.getRole());
			korisnikDTOS.add(korisnikDTO);
			
		}
		
		return new ResponseEntity<>(korisnikDTOS, HttpStatus.OK);
		
	}
	
	@PostMapping(
			value = "/login",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<KorisnikDTO> login(@RequestBody LoginKorisnikDTO korisnikDTO) throws Exception {
		
		Korisnik korisnik = korisnickiService.login(korisnikDTO.getEmail(), korisnikDTO.getLozinka());
		
		KorisnikDTO loggedKorisnik = new KorisnikDTO();
		
		if (korisnik != null && korisnik.getStatus() != false) {
			loggedKorisnik = new KorisnikDTO(korisnik.getId(), korisnik.getUserName(), korisnik.getLozinka(), korisnik.getIme(), korisnik.getPrezime(), korisnik.getEmail(),
					korisnik.getTelefon(), korisnik.getDate(), korisnik.getRole(), korisnik.getTip_korisnika(), korisnik.getStatus());
			return new ResponseEntity<>(loggedKorisnik, HttpStatus.OK);
		}
		
		return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
	}
	
	
	

}
