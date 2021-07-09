package ftn.uns.ProbaProjekat.model.dto;

public class OdradjenTerminDTO {
	private Long id;
	private String naziv;
	private String tip_treninga;
	private String datum;
	private String fullname;
	private Double ocena;
	
	public OdradjenTerminDTO() {}

	public OdradjenTerminDTO(String naziv, String tip_treninga, String datum, String fullname, Double ocena) {
		this.naziv = naziv;
		this.tip_treninga = tip_treninga;
		this.datum = datum;
		this.fullname = fullname;
		this.ocena = ocena;
	}
	
	public OdradjenTerminDTO(Long id, String naziv, String tip_treninga, String datum, String fullname, Double ocena) {
		this.id = id;
		this.naziv = naziv;
		this.tip_treninga = tip_treninga;
		this.datum = datum;
		this.fullname = fullname;
		this.ocena = ocena;
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

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	
}
