package ftn.uns.ProbaProjekat.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

@Entity
@DiscriminatorValue("trener")
public class Trener extends Korisnik{
	
//	@Column
//	private boolean prihvacen;	// true/false - da li je trener prihvacen od strane administratora

	// prosecna ocena
	@Column
	private Double avgOcena;
	
	// lista treninga koje on drzi
	@OneToMany(mappedBy = "trener", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private Set<Trening> listaTreninga = new HashSet<>();
	
	// fitness centar u kom je zaposlen
	@ManyToOne(fetch = FetchType.EAGER)
	private FitnessCentar fitnessCentar;
	
	
	/*public boolean isPrihvacen() {
		return prihvacen;
	}

	public void setPrihvacen(boolean prihvacen) {
		this.prihvacen = prihvacen;
	}*/

	public Double getAvgOcena() {
		return avgOcena;
	}
	
	public void setAvgOcena(Double avgOcena) {
		this.avgOcena = avgOcena;
	}
}
