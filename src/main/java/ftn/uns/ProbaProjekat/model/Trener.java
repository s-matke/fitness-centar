package ftn.uns.ProbaProjekat.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

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

	public Trener() {}
	
	public Trener(String userName, String lozinka, String ime, String prezime, String email, String telefon, Date date,
			String role, String tip_korisnika, Boolean status, Double avgOcena) {
		super(userName, lozinka, ime, prezime, email, telefon, date, role, tip_korisnika, status);
		this.avgOcena = avgOcena;
	}
	
	public Trener(String userName, String lozinka, String ime, String prezime, String email, String telefon, Date date,
			String role, String tip_korisnika, Boolean status, Double avgOcena, FitnessCentar fitnessCentar) {
		super(userName, lozinka, ime, prezime, email, telefon, date, role, tip_korisnika, status);
		this.avgOcena = avgOcena;
		this.fitnessCentar = fitnessCentar;
	}
	
	
	
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
