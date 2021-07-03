package ftn.uns.ProbaProjekat.model.dto;

import ftn.uns.ProbaProjekat.model.Clan;
import ftn.uns.ProbaProjekat.model.Termin;

public class PrijavaTerminaDTO {
	private Long id;
	private Clan clan;
	private Termin termin;
	private Long clan_id;
	private Long termin_id;
	
	// Upcoming events
	private String fullname;
	private Double cena;
	private String vreme;
	private String datum;
	private String oznaka;
	
	public PrijavaTerminaDTO(Long id, Clan clan, Termin termin) {
		this.id = id;
		this.clan = clan;
		this.termin = termin;
	}
	
	public PrijavaTerminaDTO(Long id, Long clan_id, Long termin_id) {
		this.id = id;
		this.clan_id = clan_id;
		this.termin_id = termin_id;
	}
	
	public PrijavaTerminaDTO(Clan clan, Termin termin) {
		this.clan = clan;
		this.termin = termin;
	}
	
	public PrijavaTerminaDTO(String fullname, Double cena, String vreme, String datum, String oznaka) {
		this.fullname = fullname;
		this.cena = cena;
		this.vreme = vreme;
		this.datum = datum;
		this.oznaka = oznaka;
	}
	
	public Long getClan_id() {
		return clan_id;
	}

	public void setClan_id(Long clan_id) {
		this.clan_id = clan_id;
	}

	public Long getTermin_id() {
		return termin_id;
	}

	public void setTermin_id(Long termin_id) {
		this.termin_id = termin_id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}

	public Termin getTermin() {
		return termin;
	}

	public void setTermin(Termin termin) {
		this.termin = termin;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
}
