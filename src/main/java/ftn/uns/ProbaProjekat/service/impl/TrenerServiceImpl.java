package ftn.uns.ProbaProjekat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.Trener;
import ftn.uns.ProbaProjekat.repository.TrenerRepository;
import ftn.uns.ProbaProjekat.service.TrenerService;

@Service
public class TrenerServiceImpl implements TrenerService{
	
	public final TrenerRepository trenerRepo;
	
	@Autowired
	public TrenerServiceImpl(TrenerRepository trenerRepo) {
		this.trenerRepo = trenerRepo;
	}
	
	@Override
	public Trener create(Trener trener) throws Exception {
		if (trener.getId() != null) {
			throw new Exception("ID must be null!");
		}
		Trener noviTrener = this.trenerRepo.save(trener);
		return noviTrener;
	}

}
