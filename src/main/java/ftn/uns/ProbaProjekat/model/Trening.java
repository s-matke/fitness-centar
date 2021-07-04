package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;
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
public class Trening implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "naziv")
	private String naziv;
	
	@Column(name = "opis")	// debatable
	private String opis;
	
	@Column(name = "tip")
	private String tip;
	
	@Column(name = "trajanje")
	private Integer trajanje;	// int/double?
	
	// n treninga : 1 trener
	@ManyToOne(fetch = FetchType.EAGER)
	private Trener trener;
	
	@OneToMany(mappedBy = "trening", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Termin> termini = new HashSet<>();

//	@OneToMany(mappedBy = "trening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
//	private Set<PrijavaTermina> listaPrijavljenihTermina = new HashSet<>();
	
	@OneToMany(mappedBy = "trening", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<OdradjenTermin> listaOdradjenihTermina = new HashSet<>();
	
	public Trening() {}
	
	public Trening(String naziv, String opis, String tip, Integer trajanje, Trener trener) {
		this.naziv = naziv;
		this.opis = opis;
		this.tip = tip;
		this.trajanje = trajanje;
		this.trener = trener;
	}
	
	public Trening(Long id, String naziv, String opis, String tip, Integer trajanje, Trener trener) {
		this.id = id;
		this.naziv = naziv;
		this.opis = opis;
		this.tip = tip;
		this.trajanje = trajanje;
		this.trener = trener;
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

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getTip_treninga() {
		return tip;
	}

	public void setTip_treninga(String tip) {
		this.tip = tip;
	}

	public Integer getTrajanje() {
		return trajanje;
	}

	public void setTrajanje(Integer trajanje) {
		this.trajanje = trajanje;
	}

	public Trener getTrener() {
		return trener;
	}

	public void setTrener(Trener trener) {
		this.trener = trener;
	}
	
	@Override
	public String toString() {
		return "Naziv: " + this.getNaziv() + 
			   "\nOpis: " + this.getOpis() + 
			   "\nTip: " + this.getTip_treninga() + 
			   "\nTrajanje: " + this.getTrajanje();
	}
	
	
}
