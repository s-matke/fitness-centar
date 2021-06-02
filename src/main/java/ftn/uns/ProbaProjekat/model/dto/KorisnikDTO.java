package ftn.uns.ProbaProjekat.model.dto;

public class KorisnikDTO {
	
	private Long id;
	private String ime;
	private String prezime;
	private Boolean status;
	
	
	public KorisnikDTO(Long id, String ime, String prezime, Boolean status) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.status = status;
	}

	public KorisnikDTO(String ime, String prezime, Boolean status) {
		this.ime = ime;
		this.prezime = prezime;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

}
