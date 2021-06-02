package ftn.uns.ProbaProjekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.Clan;
import ftn.uns.ProbaProjekat.model.dto.ClanDTO;
import ftn.uns.ProbaProjekat.service.ClanService;

@RestController
@RequestMapping(value = "/api/clan")
public class ClanController {
	
	private final ClanService clanService;
	
	@Autowired
	public ClanController(ClanService clanService) {
		this.clanService = clanService;
	}
	
	@PostMapping(
			value = "/register",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ClanDTO> createClan(@RequestBody ClanDTO clanDTO) throws Exception {
		clanDTO.setStatus(true); // svakom clanu se po default-u postavlja status na true prilikom registracije
		Clan clan= new Clan(clanDTO.getUserName(), clanDTO.getLozinka(), clanDTO.getIme(), clanDTO.getPrezime(), clanDTO.getEmail(),
				clanDTO.getTelefon(), clanDTO.getDate(), clanDTO.getRole(), clanDTO.getTip_korisnika(), clanDTO.getStatus());
		
		Clan noviClan = clanService.create(clan);
		
		ClanDTO noviClanDTO = new ClanDTO(noviClan.getId(), noviClan.getUserName(), noviClan.getLozinka(), noviClan.getIme(), noviClan.getPrezime(), noviClan.getEmail(),
				noviClan.getTelefon(), noviClan.getDate(), noviClan.getRole(), noviClan.getTip_korisnika(), noviClan.getStatus());
		
		return new ResponseEntity<>(noviClanDTO, HttpStatus.CREATED);	
	}

}
