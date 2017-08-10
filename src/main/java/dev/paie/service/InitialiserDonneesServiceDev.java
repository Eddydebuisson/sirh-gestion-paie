package dev.paie.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilsRemunerationRepository;

@Service
public class InitialiserDonneesServiceDev implements InitialiserDonneesService {

	@Autowired
	private ApplicationContext context;

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private ProfilsRemunerationRepository renumerationRepository;

	@Autowired
	private CotisationRepository cotiRepository;

	@Override
	public void initialiser() {

		context = new ClassPathXmlApplicationContext("cotisations-imposables.xml", "cotisations-non-imposables.xml",
				"entreprises.xml", "grades.xml", "profils-remuneration.xml");
		Map<String, Cotisation> coti = context.getBeansOfType(Cotisation.class);

		coti.forEach((a, b) -> cotiRepository.save(b));

		Map<String, Entreprise> entreprise = context.getBeansOfType(Entreprise.class);
		
		entreprise.forEach((a, b) -> entrepriseRepository.save(b));
		
		Map<String, Grade> grade = context.getBeansOfType(Grade.class);

		grade.forEach((a, b) -> gradeRepository.save(b));

		Map<String, ProfilRemuneration> renumeration = context.getBeansOfType(ProfilRemuneration.class);

		renumeration.forEach((a, b) -> renumerationRepository.save(b));

	}

}
