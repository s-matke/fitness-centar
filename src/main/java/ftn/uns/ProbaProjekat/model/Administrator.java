package ftn.uns.ProbaProjekat.model;

import java.io.Serializable;

import javax.persistence.*;
@Entity
@DiscriminatorValue("admin")
public class Administrator extends Korisnik {
	
}
