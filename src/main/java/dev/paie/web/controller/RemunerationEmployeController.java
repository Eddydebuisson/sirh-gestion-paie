package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

	@RequestMapping(method = RequestMethod.POST)
	public String FormCreerEmploye(@ModelAttribute("employe") RemunerationEmploye employe) {

		employeRepository.save(employe);
		
		return "formEmploye";
	}

}