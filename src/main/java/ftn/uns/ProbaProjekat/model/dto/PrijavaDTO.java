package ftn.uns.ProbaProjekat.model.dto;

public class PrijavaDTO {
	private Long clan_id;
	private Long termin_id;
	
	public PrijavaDTO(Long clan_id, Long termin_id) {
		this.clan_id = clan_id;
		this.termin_id = termin_id;
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
	
}
