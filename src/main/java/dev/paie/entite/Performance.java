package dev.paie.entite;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "performance")
public class Performance {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String nomService;
	private LocalDateTime dateHeure;
	private Long tempsExecution;

	public Performance() {

	}

	public Performance(String nomService, LocalDateTime dateHeure, Long tempsExecution) {
		super();
		this.nomService = nomService;
		this.dateHeure = dateHeure;
		this.tempsExecution = tempsExecution;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNomService() {
		return nomService;
	}

	public void setNomService(String nomService) {
		this.nomService = nomService;
	}

	public LocalDateTime getDateHeure() {
		return dateHeure;
	}

	public void setDateHeure(LocalDateTime dateHeure) {
		this.dateHeure = dateHeure;
	}

	public Long getTempsExecution() {
		return tempsExecution;
	}

	public void setTempsExecution(Long tempsExecution) {
		this.tempsExecution = tempsExecution;
	}

}
