package ftn.uns.ProbaProjekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue("clan")
public class Clan extends Korisnik {
	
	
//	@ManyToMany(mappedBy = "odradjenTrening")
//	private Set<Trening> odradjeni = new HashSet<>();
//	
//	
//	@ManyToMany(mappedBy = "prijavljenTrening")
//	private Set<Trening> prijavljeni = new HashSet<>();
	
	// lista treninga za koje su se prijavili	
	@OneToMany(mappedBy = "clan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PrijavaTermina> listaPrijavljenihTermina = new HashSet<>();

	// lista odradjenih treninga
	@OneToMany(mappedBy = "clan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OdradjenTermin> listaOdradjenihTermina = new HashSet<>();
	
		
}