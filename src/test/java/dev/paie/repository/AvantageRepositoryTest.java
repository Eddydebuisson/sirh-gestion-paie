package dev.paie.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.spring.DataSourceMySQLConfig;
import dev.paie.spring.JpaConfig;

@ContextConfiguration(classes = { ServicesConfig.class, JpaConfig.class, DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class)
public class AvantageRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

@Test
public void test_sauvegarder_lister_mettre_a_jour() {
	
	
	Avantage avantageSave = new Avantage("Super", "Richard", new BigDecimal(12.50));
	Avantage avantageModif = new Avantage(new Integer(1), "Mega", "Rossi", new BigDecimal(126.550));
		avantageRepository.save(avantageSave);
		assertThat(avantageRepository.findAll().contains(avantageSave));

		Avantage avantagetrouver = avantageRepository.findOne(1);
		avantagetrouver.setCode(avantageModif.getCode());
		avantagetrouver.setNom(avantageModif.getNom());
		avantagetrouver.setMontant(avantageModif.getMontant());
		avantageRepository.save(avantagetrouver);

		assertThat(avantageRepository.findAll().contains(avantageModif));
}

	@Test
	public void test_find_by_code() {
		
		List<Avantage> list = avantageRepository.findByCode("Mega");
		assertThat(avantageRepository.findAll().contains(list));
	}
}