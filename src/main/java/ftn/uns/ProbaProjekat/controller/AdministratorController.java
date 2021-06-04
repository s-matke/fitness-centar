package ftn.uns.ProbaProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.FitnessCentar;
import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.model.dto.FitnessCentarDTO;
import ftn.uns.ProbaProjekat.model.dto.TrenerDTO;
import ftn.uns.ProbaProjekat.service.FitnessCentarService;
import ftn.uns.ProbaProjekat.service.TrenerService;

@RestController
@RequestMapping(value = "/api/admin")
public class AdministratorController {
	
//	@Autowired
//	public final AdministratorService adminService;

//  @Autowired
//	public KorisnikService korisnickiService;
	
//	@Autowired
//	public ClanService clanService;
	
	@Autowired
	public TrenerService trenerService;
	
	@Autowired
	public FitnessCentarService fitnessService;
	
	@PutMapping(
			value = "/odobri",
			consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<TrenerDTO>> odobriTrenere(@RequestBody Long[] array) throws Exception {
		List<TrenerDTO> treneriDTO = new ArrayList<TrenerDTO>();
		
		for (int i = 0; i < array.length; ++i) {
			Long tmpId = array[i];
			Trener trenerDTO = this.trenerService.findOne(tmpId);
			
			Trener trener = new Trener(trenerDTO.getUserName(), trenerDTO.getLozinka(), trenerDTO.getIme(), trenerDTO.getPrezime(), trenerDTO.getEmail(), trenerDTO.getTelefon(), trenerDTO.getDate(), trenerDTO.getRole(),
					trenerDTO.getTip_korisnika(), trenerDTO.getStatus(), trenerDTO.getAvgOcena());
			
			trener.setId(tmpId);
			
			if (trenerDTO.getStatus() == false) {
				trener.setStatus(true);				
			} else {
				trener.setStatus(false);
			}
			
			Trener updatedTrener = trenerService.update(trener);
			
			TrenerDTO updatedTrenerDTO = new TrenerDTO(updatedTrener.getId(), updatedTrener.getUserName(), updatedTrener.getLozinka(), updatedTrener.getIme(), updatedTrener.getPrezime(), updatedTrener.getEmail(), updatedTrener.getTelefon(), updatedTrener.getDate(), updatedTrener.getRole(),
					updatedTrener.getTip_korisnika(), updatedTrener.getStatus(), updatedTrener.getAvgOcena());
			
			treneriDTO.add(updatedTrenerDTO);	
		}
		
		return new ResponseEntity<>(treneriDTO, HttpStatus.OK);
	}
	
	@PostMapping(
			value="/addCentar",
			consumes = MediaType.APPLICATION_JSON_VALUE,
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<FitnessCentarDTO> createCentar(@RequestBody FitnessCentarDTO fitnessDTO) throws Exception {
		FitnessCentar fitnessCentar = new FitnessCentar(fitnessDTO.getNaziv(), fitnessDTO.getAdresa(), fitnessDTO.getTelefon(), fitnessDTO.getEmail());
		FitnessCentar noviCentar = fitnessService.create(fitnessCentar);
		
		FitnessCentarDTO noviCentarDTO = new FitnessCentarDTO(noviCentar.getId(), noviCentar.getNaziv(), noviCentar.getAdresa(), noviCentar.getTelefon(), noviCentar.getEmail());
		
		return new ResponseEntity<>(noviCentarDTO, HttpStatus.CREATED);
	}
	
	
	
}
