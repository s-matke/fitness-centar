package ftn.uns.ProbaProjekat.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.Trening;
import ftn.uns.ProbaProjekat.repository.TreningRepository;
import ftn.uns.ProbaProjekat.service.TreningService;

@Service
public class TreningServiceImpl implements TreningService {

	@Autowired
	public TreningRepository treningRepo;
	
	
	@Override
	public List<Trening> findAll() {
		System.out.println("Usao u findALL");
		List<Trening> treninzi = this.treningRepo.findAll();
		return treninzi;
	}


	@Override
	public List<Trening> findByNaziv(String naziv) {
		List<Trening> treninzi = this.treningRepo.findByNazivOrderByNaziv(naziv);
		return treninzi;
	}

	@Override
	public List<Trening> findByTrajanje(Integer trajanje) {
		List<Trening> treninzi = this.treningRepo.findByTrajanjeOrderByTrajanje(trajanje);
		return treninzi;
	}


	@Override
	public List<Trening> findByOpis(String opis) {
		List<Trening> treninzi = this.treningRepo.findByOpisOrderByOpis(opis);
		return treninzi;
	}


	@Override
	public List<Trening> findByTip(String tip) {
		List<Trening> treninzi = this.treningRepo.findByTipOrderByTip(tip);
		return treninzi;
	}
	
	@Override
	public Trening create(Trening trening) throws Exception {
		if (trening.getId() != null) {
			throw new Exception("ID must be null!");
		}
		
		Trening noviTrening = this.treningRepo.save(trening);
		return noviTrening;
	}
	
	@Override
	public List<Trening> findByTrener(Long id) {
		List<Trening> treninzi = this.treningRepo.findAllByTrener_id(id);
		return treninzi;
	}
	
	@Override
	public Trening findOne(Long id) {
		Trening trening = this.treningRepo.getOne(id);
		return trening;
	}


	@Override
	public Trening update(Trening trening) throws Exception {
		Trening treningToUpdate = this.treningRepo.getOne(trening.getId());
		if (treningToUpdate == null) {
			throw new Exception("Trening ne postoji");
		}
		
		treningToUpdate.setNaziv(trening.getNaziv());
		treningToUpdate.setOpis(trening.getOpis());
		treningToUpdate.setTip(trening.getTip());
		treningToUpdate.setTrajanje(trening.getTrajanje());
		
		Trening savedTrening = this.treningRepo.save(treningToUpdate);
		return savedTrening;
	}


	@Override
	public void delete(Long id) {
		this.treningRepo.deleteById(id);
	}
	
}
