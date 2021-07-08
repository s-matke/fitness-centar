package ftn.uns.ProbaProjekat.model.dto;

import java.sql.Timestamp;

public class TerminDTO {

	private Long id;
	private Timestamp pocetak;
	private Double cena;
	private Long sala_id;
	private Long trening_id;
	private long epoha;
	private long epoha_kraj;
	
	private String vreme;
	private String datum;
	
	// sala trening
	private String naziv;
	private Integer trajanje;
	private String oznaka;

	
	public TerminDTO() {}
	
	public TerminDTO(long epoha, Double cena, Long trening_id) {
		this.epoha = epoha;
		this.cena = cena;
		this.trening_id = trening_id;
	}
	
	public TerminDTO(Double cena, Long sala_id) {
		this.cena = cena;
		this.sala_id = sala_id;
	}
	
	public TerminDTO(Long id, Timestamp pocetak, Double cena, Long trening_id) {
		this.id = id;
		this.pocetak = pocetak;
		this.cena = cena;
		this.trening_id = trening_id;
	}
	
	public TerminDTO(Long id, Timestamp pocetak, Double cena, Long sala_id, Long trening_id) {
		this.id = id;
		this.pocetak = pocetak;
		this.cena = cena;
		this.sala_id = sala_id;
		this.trening_id = trening_id;
	}

	public TerminDTO(Long id, String naziv, Integer trajanje, Double cena, String vreme, String datum, Long sala_id, String oznaka) {
		this.id = id;
		this.naziv = naziv;
		this.trajanje = trajanje;
		this.cena = cena;
		this.vreme = vreme;
		this.datum = datum;
		this.sala_id = sala_id;
		this.oznaka = oznaka;
	}

	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getPocetak() {
		return pocetak;
	}

	public void setPocetak(Timestamp pocetak) {
		this.pocetak = pocetak;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Long getSala_id() {
		return sala_id;
	}

	public void setSala_id(Long sala_id) {
		this.sala_id = sala_id;
	}

	public Long getTrening_id() {
		return trening_id;
	}

	public void setTrening_id(Long trening_id) {
		this.trening_id = trening_id;
	}

	public long getEpoha() {
		return epoha;
	}

	public void setEpoha(long epoha) {
		this.epoha = epoha;
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

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public long getEpoha_kraj() {
		return epoha_kraj;
	}

	public void setEpoha_kraj(long epoha_kraj) {
		this.epoha_kraj = epoha_kraj;
	}
		
	
}
