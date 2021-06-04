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
		List<Trening> treninzi = this.treningRepo.findAll();
		return treninzi;
	}
	
}
