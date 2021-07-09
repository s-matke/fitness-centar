package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Termin implements Serializable {
	// vreme pocetka termina
	// cena termina
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "datum")
	private Timestamp pocetak;	// YYYY-mm-DD HH:mm:SS
	
	@Column(name = "kraj", nullable=true)
	private Timestamp kraj;
	
	@Column(name = "cena")
	private Double cena;
	
	// "N:1" - u jednoj sali se moze odvijati N treninga
	@ManyToOne(fetch = FetchType.EAGER)
	private Sala sala;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Trening trening;
	
	@OneToMany(mappedBy = "termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PrijavaTermina> prijavljeniTermini = new HashSet<>();
	
	@OneToMany(mappedBy = "termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OdradjenTermin> odradjeniTermini = new HashSet<>();
	
	public Termin() {}
	
	public Termin(Timestamp pocetak, Double cena, Sala sala, Trening trening) {
		this.pocetak = pocetak;
		this.cena = cena;
		this.sala = sala;
		this.trening = trening;
	}
	
	public Termin(Long id, Timestamp pocetak, Double cena, Sala sala, Trening trening) {
		this.id = id;
		this.pocetak = pocetak;
		this.cena = cena;
		this.sala = sala;
		this.trening = trening;
	}
	
	// TMP Constructor
	public Termin(Timestamp pocetak, Timestamp kraj, Double cena, Sala sala, Trening trening) {
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.cena = cena;
		this.sala = sala;
		this.trening = trening;
	}
	
	public Termin(Long id, Timestamp pocetak, Timestamp kraj, Double cena, Sala sala, Trening trening) {
		this.id = id;
		this.pocetak = pocetak;
		this.kraj = kraj;
		this.cena = cena;
		this.sala = sala;
		this.trening = trening;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getPocetak() {
		return pocetak;
	}

	public void setPocetak(Timestamp pocetak) {
		this.pocetak = pocetak;
	}

	public Double getCena() {
		return cena;
	}

	public void setCena(Double cena) {
		this.cena = cena;
	}

	public Sala getSala() {
		return sala;
	}

	public void setSala(Sala sala) {
		this.sala = sala;
	}

	public Trening getTrening() {
		return trening;
	}

	public void setTrening(Trening trening) {
		this.trening = trening;
	}

	public Timestamp getKraj() {
		return kraj;
	}

	public void setKraj(Timestamp kraj) {
		this.kraj = kraj;
	}

	public Set<PrijavaTermina> getPrijavljeniTermini() {
		return prijavljeniTermini;
	}

	public void setPrijavljeniTermini(Set<PrijavaTermina> prijavljeniTermini) {
		this.prijavljeniTermini = prijavljeniTermini;
	}

	public Set<OdradjenTermin> getOdradjeniTermini() {
		return odradjeniTermini;
	}

	public void setOdradjeniTermini(Set<OdradjenTermin> odradjeniTermini) {
		this.odradjeniTermini = odradjeniTermini;
	}
	
	
	
}
