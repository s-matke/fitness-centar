package ftn.uns.ProbaProjekat.service;

import ftn.uns.ProbaProjekat.model.Clan;
import ftn.uns.ProbaProjekat.model.dto.ClanDTO;

public interface ClanService {
	
	Clan create(Clan clan) throws Exception;
	
	Clan findOne(Long id);
	
	//Clan isUserExist(String userName, String email, String telefon) throws Exception;

}
