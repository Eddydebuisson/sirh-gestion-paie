package dev.paie.service;

import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.Utilisateur;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.ProfilsRemunerationRepository;
import dev.paie.repository.UtilisateurRepository;

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

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private UtilisateurRepository utilisateurRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

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

		List<Periode> list = new ArrayList<>();
		for (int i = 0; i < 12; i++) {
			list.add(new Periode());
			list.get(i).setDateDebut(LocalDate.of(2017, i + 1, 01));
			list.get(i).setDateFin(list.get(i).getDateDebut().with(TemporalAdjusters.lastDayOfMonth()));
		}
		
		list.forEach(a -> periodeRepository.save(a));
		
		String adminpass = this.passwordEncoder.encode("admin");
		Utilisateur admin = new Utilisateur("admin", adminpass, true,
				Utilisateur.ROLES.ROLE_ADMINISTRATEUR);
		Utilisateur rossi = new Utilisateur("SuperRossi", this.passwordEncoder.encode("ILOVEJAVA"), true,
				Utilisateur.ROLES.ROLE_UTILISATEUR);
		utilisateurRepository.save(admin);
		utilisateurRepository.save(rossi);

	}

}
