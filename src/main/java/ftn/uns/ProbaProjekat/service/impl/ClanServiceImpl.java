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
	
	@Override
	public Clan findOne(Long id) {
		Clan clan = this.clanRepo.getOne(id);
		return clan;
	}
//	@Override
//	public Clan isUserExist(String userName, String email, String telefon) {
//		
//		if (this.clanRepo.findByUserName(userName) == null || this.clanRepo.findByEmail(email) == null || this.clanRepo.findByTelefon(telefon) == null) {
//			return null;			
//		}
//		else {
//			if (this.clanRepo.findByUserName(userName) != null) {
//				Clan noviClan = this.clanRepo.findByUserName(userName);
//				return noviClan;
//			} else if (this.clanRepo.findByEmail(email) != null) {
//				Clan noviClan = this.clanRepo.findByEmail(email);
//				return noviClan;
//			} else {
//				Clan noviClan = this.clanRepo.findByTelefon(telefon);
//				return noviClan;
//			}
//		}
//	}
}
