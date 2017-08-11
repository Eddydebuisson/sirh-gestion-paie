package dev.paie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/construction")
public class construction {

	@GetMapping
	public String afficherPageCreer() {
		return "construction";
	}

}