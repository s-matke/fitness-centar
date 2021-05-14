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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
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
	private String tip_treninga;
	
	@Column(name = "trajanje")
	private Integer trajanje;	// int/double?
	
	// n treninga : 1 trener
	@ManyToOne(fetch = FetchType.EAGER)
	private Trener trener;

	@OneToMany(mappedBy = "trening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<PrijavaTermina> listaPrijavljenihTermina = new HashSet<>();
	
	@OneToMany(mappedBy = "trening", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<OdradjenTermin> listaOdradjenihTermina = new HashSet<>();
	
//	@ManyToMany
//	@JoinTable(name = "odradjen_trening",
//			   joinColumns = @JoinColumn(name = "trening_id", referencedColumnName = "id"),
//			   inverseJoinColumns = @JoinColumn(name = "clan_id", referencedColumnName = "id"))
//	private Set<Clan> odradjenTrening = new HashSet<>();
//	
//	@ManyToMany
//	@JoinTable(name = "prijavljen_trening",
//			   joinColumns = @JoinColumn(name = "trening_id", referencedColumnName = "id"),
//			   inverseJoinColumns = @JoinColumn(name = "clan_id", referencedColumnName = "id"))
//	private Set<Clan> prijavljenTrening = new HashSet<>();
	
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
		return tip_treninga;
	}

	public void setTip_treninga(String tip_treninga) {
		this.tip_treninga = tip_treninga;
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
	
	
}
