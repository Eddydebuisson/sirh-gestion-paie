package dev.paie.entite;

import java.time.ZonedDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "remunerationEmploye")
public class RemunerationEmploye {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String matricule;
	@ManyToOne
	@JoinColumn(name = "entreprise")
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name = "profilRemuneration")
	private ProfilRemuneration profilRemuneration;
	@ManyToOne
	@JoinColumn(name = "grade")
	private Grade grade;
	private ZonedDateTime datecreation;
	
	public ZonedDateTime getDatecreation() {
		return datecreation;
	}

	public void setDatecreation(ZonedDateTime datecreation) {
		this.datecreation = datecreation;
	}
	public String getMatricule() {
		return matricule;
	}
	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}
	
	public Entreprise getEntreprise() {
		return entreprise;
	}
	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}
	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}
	public void setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}
	public Grade getGrade() {
		return grade;
	}
	public void setGrade(Grade grade) {
		this.grade = grade;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	
	
	

}
