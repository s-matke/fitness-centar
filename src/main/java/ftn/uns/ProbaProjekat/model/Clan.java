package ftn.uns.ProbaProjekat.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

@Entity
@DiscriminatorValue("clan")
public class Clan extends Korisnik {
	
	
//	@ManyToMany(mappedBy = "odradjenTrening")
//	private Set<Trening> odradjeni = new HashSet<>();
//	
//	
//	@ManyToMany(mappedBy = "prijavljenTrening")
//	private Set<Trening> prijavljeni = new HashSet<>();
	
	public Clan() {
		super();
	}
	
	public Clan(String userName, String lozinka, String ime, String prezime, String email, String telefon, Date date,
			String role, String tip_korisnika, Boolean status) {
		super(userName, lozinka, ime, prezime, email, telefon, date, role, tip_korisnika, status);
	}
	
	public Clan(Long id, String userName, String lozinka, String ime, String prezime, String email, String telefon, Date date,
			String role, String tip_korisnika, Boolean status) {
		super(id, userName, lozinka, ime, prezime, email, telefon, date, role, tip_korisnika, status);
	}
	
	// lista treninga za koje su se prijavili	
	@OneToMany(mappedBy = "clan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PrijavaTermina> listaPrijavljenihTermina = new HashSet<>();

	// lista odradjenih treninga
	@OneToMany(mappedBy = "clan", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OdradjenTermin> listaOdradjenihTermina = new HashSet<>();
	
		
}