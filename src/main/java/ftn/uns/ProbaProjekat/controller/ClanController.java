package ftn.uns.ProbaProjekat.controller;

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
import ftn.uns.ProbaProjekat.model.dto.ClanDTO;
import ftn.uns.ProbaProjekat.model.dto.TermingDTO;
import ftn.uns.ProbaProjekat.service.ClanService;
import ftn.uns.ProbaProjekat.service.PrijavaTerminaService;

@RestController
@RequestMapping(value = "/api/clan")
public class ClanController {
	
	private final ClanService clanService;
	private final PrijavaTerminaService prijavaService;
	
	@Autowired
	public ClanController(ClanService clanService, PrijavaTerminaService prijavaService) {
		this.clanService = clanService;
		this.prijavaService = prijavaService;
	}
	
	@PostMapping(
			value = "/register",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClanDTO> createClan(@RequestBody ClanDTO clanDTO) throws Exception {
//		if (clanService.isUserExist(clanDTO.getUserName(), clanDTO.getEmail(), clanDTO.getTelefon()) != null) {
//			return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//		}
		
		clanDTO.setStatus(true); // svakom clanu se po default-u postavlja status na true prilikom registracije
		Clan clan= new Clan(clanDTO.getUserName(), clanDTO.getLozinka(), clanDTO.getIme(), clanDTO.getPrezime(), clanDTO.getEmail(),
				clanDTO.getTelefon(), clanDTO.getDate(), clanDTO.getRole(), clanDTO.getTip_korisnika(), clanDTO.getStatus());
		
		Clan noviClan = clanService.create(clan);
		
		ClanDTO noviClanDTO = new ClanDTO(noviClan.getId(), noviClan.getUserName(), noviClan.getLozinka(), noviClan.getIme(), noviClan.getPrezime(), noviClan.getEmail(),
				noviClan.getTelefon(), noviClan.getDate(), noviClan.getRole(), noviClan.getTip_korisnika(), noviClan.getStatus());
		
		return new ResponseEntity<>(noviClanDTO, HttpStatus.CREATED);	
	}
	

	
}
