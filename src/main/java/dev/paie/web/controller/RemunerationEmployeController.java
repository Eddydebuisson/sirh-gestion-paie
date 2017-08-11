package dev.paie.web.controller;

import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilsRemunerationRepository;
import dev.paie.repository.RenumerationEmployeRepository;
import dev.paie.web.controller.form.EmployeForm;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private ProfilsRemunerationRepository renumerationRepository;

	@Autowired
	private RenumerationEmployeRepository employeRepository;
	

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");

		List<Entreprise> entreprise = entrepriseRepository.findAll();
		mv.addObject("entreprise", entreprise);

		List<Grade> grade = gradeRepository.findAll();
		mv.addObject("grade", grade);

		List<ProfilRemuneration> profils = renumerationRepository.findAll();
		mv.addObject("profil", profils);

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView afficherEmploye() {
		ModelAndView mv = new ModelAndView();

		List<RemunerationEmploye> profils = employeRepository.findAll();
		mv.addObject("listeEmploye", profils);

		return mv;

	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String FormCreerEmploye(@ModelAttribute("employe") EmployeForm employe) {
		RemunerationEmploye nouveau = new RemunerationEmploye();
		ZonedDateTime date = ZonedDateTime.now();
		nouveau.setMatricule(employe.getMatricule());
		nouveau.setEntreprise(entrepriseRepository.findByDenomination(employe.getEntreprise()));
		nouveau.setGrade(gradeRepository.findByCode(employe.getGrade()));
		nouveau.setProfilRemuneration(renumerationRepository.findByCode(employe.getProfil()));
		nouveau.setDatecreation(date);
		employeRepository.save(nouveau);
		
		return "redirect:lister";
	}

}