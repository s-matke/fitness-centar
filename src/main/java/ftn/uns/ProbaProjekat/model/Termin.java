package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Termin implements Serializable {
	// vreme pocetka termina
	// cena termina
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "vreme_pocetka")
	private String pocetak;
	
	@Column(name = "cena")
	private Double cena;
	
	// "N:1" - u jednoj sali se moze odvijati N treninga
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Sala sala;
	
	@OneToMany(mappedBy="termin", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PrijavaTermina> prijavljeniTermini = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getPocetak() {
		return pocetak;
	}

	public void setPocetak(String pocetak) {
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
	
	
}
