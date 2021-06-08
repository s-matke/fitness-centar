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
	public List<Trening> findAll(String keyword) {
		if (keyword == null) {
			System.out.println("Usao u findALL");
			List<Trening> treninzi = this.treningRepo.findAll();
			return treninzi;
		}
		System.out.println();
		List<Trening> treninzi = this.treningRepo.search(keyword);
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
	
	
	
}
