package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Grade;
import dev.paie.entite.Periode;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.PeriodeRepository;

@Controller
@RequestMapping("/bulletin")
public class BulletinController {

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	// @Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerBulletin");

		List<Periode> periode = periodeRepository.findAll();
		mv.addObject("periode", periode);

		List<Grade> grade = gradeRepository.findAll();
		mv.addObject("grade", grade);


		return mv;
	}

}
