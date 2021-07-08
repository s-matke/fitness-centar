package ftn.uns.ProbaProjekat.model.dto;

public class FitnessCentarDTO {
	
	private Long id;
	private String naziv;
	private String adresa;
	private String telefon;
	private String email;
	
	// Izlistavanjje za brisanje
	private Integer treneri;
	private Integer sale;
	
	
	public FitnessCentarDTO() {}
	
	public FitnessCentarDTO(String naziv, String adresa, String telefon, String email) {
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
	}
	
	
	public FitnessCentarDTO(Long id, String naziv, String adresa, String telefon, String email) {
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
	}
	
	public FitnessCentarDTO(Long id, String naziv, String adresa, Integer sale, Integer treneri) {
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.sale = sale;
		this.treneri = treneri;
	}

	public FitnessCentarDTO(Long id, String naziv, String adresa, String telefon, String email, Integer sale, Integer treneri) {
		this.id = id;
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
		this.sale = sale;
		this.treneri = treneri;
	}
	
	public FitnessCentarDTO(Long id, String naziv) {
		this.id = id;
		this.naziv = naziv;
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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public String getTelefon() {
		return telefon;
	}

	public void setTelefon(String telefon) {
		this.telefon = telefon;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getTreneri() {
		return treneri;
	}

	public void setTreneri(Integer treneri) {
		this.treneri = treneri;
	}

	public Integer getSale() {
		return sale;
	}

	public void setSale(Integer sale) {
		this.sale = sale;
	}
	
}
