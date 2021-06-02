package ftn.uns.ProbaProjekat.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ftn.uns.ProbaProjekat.model.Clan;
import ftn.uns.ProbaProjekat.repository.ClanRepository;
import ftn.uns.ProbaProjekat.service.ClanService;

@Service
public class ClanServiceImpl implements ClanService {
	
	public final ClanRepository clanRepo;
	
	@Autowired
	public ClanServiceImpl(ClanRepository clanRepo) {
		this.clanRepo = clanRepo;
	}

	@Override
	public Clan create(Clan clan) throws Exception {
		
		if (clan.getId() != null) {
			throw new Exception("ID must be null!");
		}
		Clan noviClan = this.clanRepo.save(clan);
		return noviClan;
	}
}
