package ftn.uns.ProbaProjekat.model.dto;

public class SalaDTO {
	private Long id;
	private String oznaka;
	
	public SalaDTO() {}
	
	public SalaDTO(Long id, String oznaka) {
		this.id = id;
		this.oznaka = oznaka;
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
	
	
}
