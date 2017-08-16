package dev.paie.web.controller;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Periode;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RenumerationEmployeRepository;
import dev.paie.web.controller.form.BullletinForm;

@Controller
@RequestMapping("/bulletin")
public class BulletinController {

	@Autowired
	private PeriodeRepository periodeRepository;

	@Autowired
	private RenumerationEmployeRepository renumerationRepository;

	@Autowired
	private BulletinRepository bulletinRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/creerBulletin");

		List<Periode> periode = periodeRepository.findAll();
		mv.addObject("periode", periode);

		List<RemunerationEmploye> listeEmploye = renumerationRepository.findAll();
		mv.addObject("matricule", listeEmploye);


		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured({ "ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR" })
	public ModelAndView afficherBulletin() {
		ModelAndView mv = new ModelAndView();

		List<BulletinSalaire> bulletin = bulletinRepository.findAll();
		mv.addObject("bulletin", bulletin);

		return mv;

	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public String FormCreerEmploye(@ModelAttribute("bulletin") BullletinForm bulletin) {
		BulletinSalaire nouveau = new BulletinSalaire();
		ZonedDateTime date = ZonedDateTime.now();
		nouveau.setPeriode(periodeRepository.findById(new Integer(bulletin.getPeriode())));
		nouveau.setRemunerationEmploye(renumerationRepository.findById(new Integer(bulletin.getMatricule())));
		nouveau.setPrimeExceptionnelle(new BigDecimal(bulletin.getPrime()));
		nouveau.setDateCreation(date);
		bulletinRepository.save(nouveau);

		return "redirect:lister";
	}

}
