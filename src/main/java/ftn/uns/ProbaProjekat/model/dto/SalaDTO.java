package ftn.uns.ProbaProjekat.model.dto;

public class SalaDTO {
	private Long id;
	private String oznaka;
	private Integer kapacitet;
	
	// Centar
	private Long centar_id;
	private String naziv;
	
	public SalaDTO() {}
	
	public SalaDTO(Long id, String oznaka) {
		this.id = id;
		this.oznaka = oznaka;
	}
	
	public SalaDTO(String oznaka, Integer kapacitet, Long centar_id) {
		this.oznaka = oznaka;
		this.kapacitet = kapacitet;
		this.centar_id = centar_id;
	}

	public SalaDTO(String oznaka, Integer kapacitet, Long centar_id, String naziv) {
		this.oznaka = oznaka;
		this.kapacitet = kapacitet;
		this.centar_id = centar_id;
		this.naziv = naziv;
	}
	
	public SalaDTO(Long id, String oznaka, Integer kapacitet, Long centar_id, String naziv) {
		this.id = id;
		this.oznaka = oznaka;
		this.kapacitet = kapacitet;
		this.centar_id = centar_id;
		this.naziv = naziv;
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Integer getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(Integer kapacitet) {
		this.kapacitet = kapacitet;
	}

	public Long getCentar_id() {
		return centar_id;
	}

	public void setCentar_id(Long centar_id) {
		this.centar_id = centar_id;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}	
}
