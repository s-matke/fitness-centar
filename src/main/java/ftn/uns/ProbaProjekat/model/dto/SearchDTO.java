package ftn.uns.ProbaProjekat.model.dto;

import java.sql.Date;

public class SearchDTO {

	private Long id;
	private String naziv;
	private String tip_treninga;
	private String opis;
	private Double cenaOd;
	private Double cenaDo;
	private Date date;
	
	public SearchDTO() {}
	
	public SearchDTO(Long id, String naziv, String tip_treninga, String opis, Double cenaOd, Double cenaDo, Date date) {
		this.id = id;
		this.naziv = naziv;
		this.tip_treninga = tip_treninga;
		this.opis = opis;
		this.cenaOd = cenaOd;
		this.cenaDo = cenaDo;
		this.date = date;
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

	public String getTip_treninga() {
		return tip_treninga;
	}

	public void setTip_treninga(String tip_treninga) {
		this.tip_treninga = tip_treninga;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}
	
	public Double getCenaOd() {
		return cenaOd;
	}

	public void setCenaOd(Double cenaOd) {
		this.cenaOd = cenaOd;
	}

	public Double getCenaDo() {
		return cenaDo;
	}

	public void setCenaDo(Double cenaDo) {
		this.cenaDo = cenaDo;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
	
}
