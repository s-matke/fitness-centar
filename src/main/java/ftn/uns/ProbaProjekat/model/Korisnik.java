package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.*;

import static javax.persistence.DiscriminatorType.STRING;
import static javax.persistence.InheritanceType.SINGLE_TABLE;

@Entity
@Table(name="korisnik")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tip_korisnika", discriminatorType=DiscriminatorType.STRING)
public class Korisnik implements Serializable{
	
	/**
	 * 
	 */
	//private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
//	@Column(insertable=false, updatable=false)
//	private String tip_korisnika;
	
	@Column(name = "user_name", unique = true)
	private String userName;
	
	@Column
	private String lozinka;
	
	@Column(name = "first_name")
	private String ime;
	
	@Column(name = "last_name")
	private String prezime;
	
	@Column(unique=true)
	private String telefon;
	
	@Column(unique=true)	// kako bi zabranili registraciju korisnika sa istom adresom
	private String email;
	
	@Column(name = "birthday")
	private Date date;
	//private String date;
	
	@Column(name = "uloga")
	private String role;
	
	@Column(nullable = false)
	private Boolean status;	// true/false
		
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLozinka() {	// protected
		return lozinka;
	}

	public void setLozinka(String lozinka) {
		this.lozinka = lozinka;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	

	
}
