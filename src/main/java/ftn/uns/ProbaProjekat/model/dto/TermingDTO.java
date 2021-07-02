package ftn.uns.ProbaProjekat.model.dto;

public class TermingDTO {
	
	private Long id;
	private String naziv;
	private String opis;
	private String tip;
	private Integer trajanje;
	private String datum;
	private String vreme;
	private Double cena;
	private String ime_prezime;
	private String oznaka;
	
	public TermingDTO(String naziv, String opis, Double cena, String datum, String vreme, String ime_prezime, Long id) {
		this.naziv = naziv;
		this.opis = opis;
		this.cena = cena;
		this.datum = datum;
		this.vreme = vreme;
		this.ime_prezime = ime_prezime;
		this.id = id;
	}
		
	public TermingDTO(String naziv, String opis, String tip, Integer trajanje, String datum, String vreme, Double cena, String ime_prezime, String oznaka) {
		this.naziv = naziv;
		this.opis = opis;
		this.tip = tip;
		this.trajanje = trajanje;
		this.datum = datum;
		this.vreme = vreme;
		this.cena = cena;
		this.ime_prezime = ime_prezime;
		this.oznaka = oznaka;
	}
	
	public TermingDTO(String naziv, String opis, String tip, Integer trajanje, String datum, String vreme, Double cena, String ime_prezime, String oznaka, Long id) {
		this.naziv = naziv;
		this.opis = opis;
		this.tip = tip;
		this.trajanje = trajanje;
		this.datum = datum;
		this.vreme = vreme;
		this.cena = cena;
		this.ime_prezime = ime_prezime;
		this.oznaka = oznaka;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTip() {
		return tip;
	}

	public void setTip(String tip) {
		this.tip = tip;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getVreme() {
		return vreme;
	}

	public void setVreme(String vreme) {
		this.vreme = vreme;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public String getIme_prezime() {
		return ime_prezime;
	}

	public void setIme_prezime(String ime_prezime) {
		this.ime_prezime = ime_prezime;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	
}
