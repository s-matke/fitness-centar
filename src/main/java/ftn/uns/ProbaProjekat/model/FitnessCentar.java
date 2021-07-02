package ftn.uns.ProbaProjekat.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
public class FitnessCentar implements Serializable{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String naziv;	// naziv fitness centra
	
	@Column
	private String adresa;	// adresa fitness centra
	
	@Column
	private String telefon;	// br. telefona
	
	@Column
	private String email;	// mail fit. centra
	
	public FitnessCentar() {}
	
	public FitnessCentar(String naziv, String adresa, String telefon, String email) {
		this.naziv = naziv;
		this.adresa = adresa;
		this.telefon = telefon;
		this.email = email;
	}
	
	// lista trenera koji rade u teretani
	// 1:n (1 teretana : n trenera)
	@OneToMany(mappedBy = "fitnessCentar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Trener> treneri = new HashSet<>();
	
	// lista sala u fitness centru
	@OneToMany(mappedBy = "fitnessCentar", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Sala> listaSala = new HashSet<>();

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

	public Set<Trener> getTreneri() {
		return treneri;
	}

	public void setTrener(Set<Trener> treneri) {
		this.treneri = treneri;
	}

	public Set<Sala> getListaSala() {
		return listaSala;
	}

	public void setListaSala(Set<Sala> listaSala) {
		this.listaSala = listaSala;
	}	
}
