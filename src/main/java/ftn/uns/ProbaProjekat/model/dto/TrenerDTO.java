package ftn.uns.ProbaProjekat.model.dto;

import java.sql.Date;

public class TrenerDTO {
	
	private Long id;
	private String userName;
	private String lozinka;
	private String ime;
	private String prezime;
	private String telefon;
	private String email;
	private Date date;
	private String role;
	private Boolean status;
	private Double avgOcena;
	private String tip_korisnika;
	private Long fitnessCentar_id;
	
	private String fullname;
	
	public TrenerDTO() {
	}
	
	public TrenerDTO(String userName, String fullname, String email, String telefon, String role, Double avgOcena) {
		this.userName = userName;
		this.fullname = fullname;
		this.email = email;
		this.telefon = telefon;
		this.role = role;
		this.avgOcena = avgOcena;
	}
	
	public TrenerDTO(Long id, String userName, String lozinka, String ime, String prezime, String email,
			String telefon, Date date, String role, String tip_korisnika, Boolean status, Double avgOcena) {
		this.id = id;
		this.userName = userName;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.telefon = telefon;
		this.date = date;
		this.role = role;
		this.tip_korisnika = tip_korisnika;
		this.status = status;
		this.avgOcena = avgOcena;
	}

	public TrenerDTO(String userName, String lozinka, String ime, String prezime, String email,
			String telefon, Date date, String role, String tip_korisnika, Boolean status, Double avgOcena, Long fitnessCentar_id) {
		//this.id = id;
		this.userName = userName;
		this.lozinka = lozinka;
		this.ime = ime;
		this.prezime = prezime;
		this.email = email;
		this.telefon = telefon;
		this.date = date;
		this.role = role;
		this.tip_korisnika = tip_korisnika;
		this.status = status;
		this.avgOcena = avgOcena;
		this.fitnessCentar_id = fitnessCentar_id;
	}
	
	public TrenerDTO(Long id, String ime, String prezime, Boolean status) {
		this.id = id;
		this.ime = ime;
		this.prezime = prezime;
		this.status = status;
	}

	public TrenerDTO(String ime, String prezime, Boolean status) {
		this.ime = ime;
		this.prezime = prezime;
		this.status = status;
	}

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

	public String getLozinka() {
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

	public Double getAvgOcena() {
		return avgOcena;
	}

	public void setAvgOcena(Double avgOcena) {
		this.avgOcena = avgOcena;
	}

	public String getTip_korisnika() {
		return tip_korisnika;
	}

	public void setTip_korisnika(String tip_korisnika) {
		this.tip_korisnika = tip_korisnika;
	}

	public Long getFitnessCentar_id() {
		return fitnessCentar_id;
	}

	public void setFitnessCentar_id(Long fitnessCentar_id) {
		this.fitnessCentar_id = fitnessCentar_id;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
}
