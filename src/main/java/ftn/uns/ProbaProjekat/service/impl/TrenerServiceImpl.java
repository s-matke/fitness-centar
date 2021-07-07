package ftn.uns.ProbaProjekat.service.impl;

import java.util.List;

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
	public Trener findOne(Long id) {
		Trener trener = this.trenerRepo.getOne(id);
		return trener;
	}
	
	@Override
	public List<Trener> findAll() {
		List<Trener> treneri = this.trenerRepo.findAll();
		return treneri;
	}


	@Override
	public Trener create(Trener trener) throws Exception {
		if (trener.getId() != null) {
			throw new Exception("ID must be null!");
		}
		Trener noviTrener = this.trenerRepo.save(trener);
		return noviTrener;
	}
	
	@Override
	public Trener update(Trener trener) throws Exception {
		Trener trenerToUpdate = this.trenerRepo.getOne(trener.getId());
		if (trenerToUpdate == null) {
			throw new Exception("Trener ne postoji!");
		}
		
		trenerToUpdate.setStatus(trener.getStatus());
		
		Trener savedTrener = this.trenerRepo.save(trenerToUpdate);
		return savedTrener;
	}
	
	@Override
	public List<Trener> findByCentar(Long id) {
		if (id == null) {
			return null;
		}
		List<Trener> treneri = this.trenerRepo.findAllByFitnessCentar_Id(id);
		return treneri;
	}

	@Override
	public void delete(Long id) {
		this.trenerRepo.deleteById(id);
		
	}
}	
