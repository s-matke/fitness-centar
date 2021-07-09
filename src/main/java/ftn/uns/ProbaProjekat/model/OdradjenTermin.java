package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class OdradjenTermin implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Double ocena;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Termin termin;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Trener trener;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Clan clan;
	
	
	/*
	 	ocena  |  termin_id   |  clan_id |  trener_id
	 	-------|--------------|----------------------
	 *    3.5  |      1       |    2     |     1
	 *
	 *ocena - ocena clan-a za dati odradjen termin
	 *trening - info o treningu (u treningu se nalazi polje trener_id)
	 *clan - info o clanu koji je odradio trening
	 */

	public OdradjenTermin() {}
	
	public OdradjenTermin(Termin termin, Clan clan) {
		this.termin = termin;
		this.clan = clan;
	}
	
	public OdradjenTermin(Termin termin, Trener trener, Clan clan) {
		this.termin = termin;
		this.trener = trener;
		this.clan = clan;
	}
	
	public OdradjenTermin(Double ocena, Termin termin, Trener trener, Clan clan) {
		this.ocena = ocena;
		this.trener = trener;
		this.termin = termin;
		this.clan = clan;
	}
	
	public OdradjenTermin(Long id, Double ocena, Termin termin, Trener trener, Clan clan) {
		this.id = id;
		this.ocena = ocena;
		this.termin = termin;
		this.trener = trener;
		this.clan = clan;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
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
	
	
}
