package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

@ContextConfiguration(classes = { ServicesConfig.class, JpaConfig.class, DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class)
public class CotisationServiceJpaTest {

	@Autowired
	private CotisationService cotisationService;

@Test
public void test_sauvegarder_lister_mettre_a_jour() {
		Cotisation cotiSave = new Cotisation("SuperRossi", "SpringJpa", new BigDecimal(1.000), new BigDecimal(12.50));
		Cotisation cotiModif = new Cotisation(new Integer(1), "SuperRichard", "SpringJpa2", new BigDecimal(100.057),
				new BigDecimal(126.550));
		cotisationService.sauvegarder(cotiSave);
	assertThat(cotisationService.lister().contains(cotiSave));

	cotisationService.mettreAJour(cotiModif);
	assertThat(cotisationService.lister().contains(cotiModif));
}
}
