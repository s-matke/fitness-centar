package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class PrijavaTermina implements Serializable {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
//	@Column
//	private Integer brPrijavljenihClanova;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Termin termin;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Clan clan;
	
//	@ManyToOne(fetch = FetchType.EAGER)
//	private Trening trening;
	
	public PrijavaTermina() {}
	
	public PrijavaTermina(Clan clan, Termin termin) {
		this.clan = clan;
		this.termin = termin;
	}
	
	public PrijavaTermina(Long id, Clan clan, Termin termin) {
		this.id = id;
		this.clan = clan;
		this.termin = termin;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}
	
	@Override
	public String toString() {
		return "Clan: " + this.getClan().getIme() + " " + this.getClan().getPrezime() +
			   "\nTermin: " + this.getTermin().getCena() + ", " +this.getTermin().getPocetak();
	}

//	public Trening getTrening() {
//		return trening;
//	}
//
//	public void setTrening(Trening trening) {
//		this.trening = trening;
//	}
	
	/*
	 * 
	 * termin_id | clan_id | trening_id
	 * ----------|---------|-------------
	 * 		1	 |	  1	   |    2
	
	 *termin - vreme pocetka, cena
	 *clan - clan koji je zakazao termin
	 *trening - info o treningu (tip, naziv, trajanje itd)
	 */
	
	

}

