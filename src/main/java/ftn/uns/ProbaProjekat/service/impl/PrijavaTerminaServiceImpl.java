package ftn.uns.ProbaProjekat.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.PrijavaTermina;
import ftn.uns.ProbaProjekat.repository.PrijavaTerminaRepository;
import ftn.uns.ProbaProjekat.service.PrijavaTerminaService;

@Service
public class PrijavaTerminaServiceImpl implements PrijavaTerminaService {
	
	public final PrijavaTerminaRepository prijavaRepo;
	
	@Autowired
	public PrijavaTerminaServiceImpl(PrijavaTerminaRepository prijavaRepo) {
		this.prijavaRepo = prijavaRepo;
	}
	
	@Override
	public PrijavaTermina create(PrijavaTermina prijava) throws Exception {
		if (prijava.getId() != null) {
			throw new Exception("ID must be null");
		}
		PrijavaTermina novaPrijava = this.prijavaRepo.save(prijava);
		return novaPrijava;
	}
	
	@Override
	public List<PrijavaTermina> findByClan(Long id) {
		if (id == null) {
			return null;
		}
		
		List<PrijavaTermina> termini = this.prijavaRepo.findAllByClan_id(id);
		return termini;
	}
	
	@Override
	public void delete(Long id) {
		this.prijavaRepo.deleteById(id);
	}
	
	@Override
	public PrijavaTermina findOne(Long id) {
		return this.prijavaRepo.getOne(id);
	}

	@Override
	public List<PrijavaTermina> findAllById(Long id) {
		List<PrijavaTermina> lista = this.prijavaRepo.search(id);
		return lista;
	}

	@Override
	public int slobodnaMesta(Timestamp pocetak, Timestamp kraj) {
		List<PrijavaTermina> listaTermina = this.prijavaRepo.novomesto(pocetak, kraj);
		System.out.println("Termini izmedju: " + pocetak + " ---- " + kraj);
		for (PrijavaTermina termina : listaTermina) {
			System.out.println(termina.getTermin().getPocetak() + " -- " + termina.getTermin().getKraj());
		}
		return listaTermina.size();
	}


}
