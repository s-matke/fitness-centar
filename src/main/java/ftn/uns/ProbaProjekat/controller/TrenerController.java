package ftn.uns.ProbaProjekat.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.model.dto.TrenerDTO;
import ftn.uns.ProbaProjekat.service.TrenerService;


@RestController
@RequestMapping(value = "/api/trener")
public class TrenerController {
	private final TrenerService trenerService;
	
	@Autowired
	public TrenerController(TrenerService trenerService) {
		this.trenerService = trenerService;
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
}
