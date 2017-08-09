package dev.paie.service;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;
import dev.paie.spring.DataSourceMySQLConfig;

@ContextConfiguration(classes = { ServicesConfig.class, DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class)
public class GradeServiceJdbcTemplateTest {
	
	@Autowired
	private GradeService gradeService;


@Test
public void test_sauvegarder_lister_mettre_a_jour() {

		Grade gradeSave = new Grade("java", new BigDecimal(1.000), new BigDecimal(12.50));
		Grade gradeModif = new Grade(new Integer(1), "Sql", new BigDecimal(25.120), new BigDecimal(123.50));
		gradeService.sauvegarder(gradeSave);
		assertThat(gradeService.lister().contains(gradeSave));

		gradeService.mettreAJour(gradeModif);
		assertThat(gradeService.lister().contains(gradeModif));
}
}