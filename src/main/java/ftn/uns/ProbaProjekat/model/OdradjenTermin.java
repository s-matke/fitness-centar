package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;

import javax.persistence.*;

@Entity
public class OdradjenTermin implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private Double ocena;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Trening trening;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Clan clan;
	
	
	/*
	 	ocena  |  trening_id  |  clan_id
	 	-------|--------------|---------
	 *    3.5  |      1       |    2
	 *
	 *ocena - ocena clan-a za dati odradjen termin
	 *trening - info o treningu (u treningu se nalazi polje trener_id)
	 *clan - info o clanu koji je odradio trening
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Double getOcena() {
		return ocena;
	}

	public void setOcena(Double ocena) {
		this.ocena = ocena;
	}

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public Clan getClan() {
		return clan;
	}

	public void setClan(Clan clan) {
		this.clan = clan;
	}
	
	
}
