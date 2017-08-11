package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import dev.paie.entite.ProfilRemuneration;

public interface ProfilsRemunerationRepository extends JpaRepository<ProfilRemuneration, Integer> {

	ProfilRemuneration findByCode(String code);

}
