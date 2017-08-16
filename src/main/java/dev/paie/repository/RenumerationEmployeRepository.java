package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.RemunerationEmploye;

public interface RenumerationEmployeRepository extends JpaRepository<RemunerationEmploye, Integer> {


	RemunerationEmploye findByMatricule(String matricule);

	RemunerationEmploye findById(Integer id);

}
