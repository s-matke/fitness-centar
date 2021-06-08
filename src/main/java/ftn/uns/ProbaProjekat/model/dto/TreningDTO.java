package ftn.uns.ProbaProjekat.model.dto;

public class TreningDTO {

	private Long id;
	private String naziv;
	private String opis;
	private String tip;
	private Integer trajanje;
	
	public TreningDTO() {}
	
	public TreningDTO(Long id, String naziv, String opis, String tip, Integer trajanje) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.tip = tip;
		this.trajanje = trajanje;
	}
	
	public TreningDTO(String naziv, String opis, String tip, Integer trajanje) {
		this.naziv = naziv;
		this.opis = opis;
		this.tip = tip;
		this.trajanje = trajanje;
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

	public String getTip_treninga() {
		return tip;
	}

	public void setTip_treninga(String tip) {
		this.tip = tip;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}	
}
