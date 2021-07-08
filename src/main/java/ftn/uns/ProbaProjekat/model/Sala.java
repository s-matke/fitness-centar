package ftn.uns.ProbaProjekat.model;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Set;
import java.util.HashSet;

@Entity
public class Sala implements Serializable{
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "kapacitet")
	private Integer kapacitet;
	
	@Column(name = "oznaka")
	private String oznaka;
	
	// 1:n (1 sala : n treninga)
	@OneToMany(mappedBy = "sala", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
	private Set<Termin> listaTermina = new HashSet<>();

	// n sala : 1 fitness centar
	@ManyToOne(fetch = FetchType.EAGER)
	private FitnessCentar fitnessCentar;
	
	public Sala() {}

	public Sala(String oznaka, Integer kapacitet, FitnessCentar centar) {
		this.oznaka = oznaka;
		this.kapacitet = kapacitet;
		this.fitnessCentar = centar;
	}
	
	public Sala(Long id, String oznaka, Integer kapacitet, FitnessCentar centar) {
		this.id = id;
		this.oznaka = oznaka;
		this.kapacitet = kapacitet;
		this.fitnessCentar = centar;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getKapacitet() {
		return kapacitet;
	}

	public void setKapacitet(Integer kapacitet) {
		this.kapacitet = kapacitet;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}

	public Set<Termin> getListaTermina() {
		return listaTermina;
	}

	public void setListaTermina(Set<Termin> listaTermina) {
		this.listaTermina = listaTermina;
	}

	public FitnessCentar getFitnessCentar() {
		return fitnessCentar;
	}

	public void setFitnessCentar(FitnessCentar fitnessCentar) {
		this.fitnessCentar = fitnessCentar;
	}
	
}
