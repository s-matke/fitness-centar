package ftn.uns.ProbaProjekat.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ftn.uns.ProbaProjekat.model.FitnessCentar;
import ftn.uns.ProbaProjekat.model.dto.FitnessCentarDTO;
import ftn.uns.ProbaProjekat.service.FitnessCentarService;

@RestController
@RequestMapping(value="/api/centar")
public class FitnessCentarController {
	
	private final FitnessCentarService centarService;
	
	@Autowired
	public FitnessCentarController(FitnessCentarService centarService) {
		this.centarService = centarService;
	}
	
	@GetMapping(
			value = "/opcije",
			produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<FitnessCentarDTO>> listCentar() {
		List<FitnessCentarDTO> centarDTOS = new ArrayList<>();
		List<FitnessCentar> listaCentara = this.centarService.findAll();
		
		for (FitnessCentar centar : listaCentara) {
			FitnessCentarDTO centarDTO = new FitnessCentarDTO(centar.getId(), centar.getNaziv());
			centarDTOS.add(centarDTO);
		}
		
		return new ResponseEntity<>(centarDTOS, HttpStatus.OK);
	}

}
