package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.utils.PaieUtils;


@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Autowired
	private PaieUtils paieUtils;

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		List<Cotisation> cotiNonImposable = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsNonImposables();

		List<Cotisation> cotiImposable = bulletin.getRemunerationEmploye().getProfilRemuneration()
				.getCotisationsImposables();

		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		BigDecimal salaireDeBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		resultat.setSalaireDeBase(paieUtils.formaterBigDecimal(salaireDeBase));

		BigDecimal salaireBrut = salaireDeBase.add(bulletin.getPrimeExceptionnelle());
		resultat.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBrut));


		BigDecimal cotiSalarial = cotiNonImposable.stream().filter(p -> p.getTauxSalarial() != null)
				.map(p -> p.getTauxSalarial().multiply(new BigDecimal(resultat.getSalaireBrut())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		resultat.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(cotiSalarial));

		BigDecimal cotipatro = cotiNonImposable.stream().filter(p -> p.getTauxPatronal() != null)
				.map(p -> p.getTauxPatronal().multiply(new BigDecimal(resultat.getSalaireBrut())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);
		resultat.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(cotipatro));

		BigDecimal netPayer = new BigDecimal(resultat.getSalaireBrut())
				.subtract(new BigDecimal(resultat.getTotalRetenueSalarial()));
		
		resultat.setNetImposable(paieUtils.formaterBigDecimal(netPayer));
		
		BigDecimal SumCotiMultiSalaire = cotiImposable.stream().filter(p -> p.getTauxSalarial() != null)
				.map(p -> p.getTauxSalarial().multiply(new BigDecimal(resultat.getSalaireBrut())))
				.reduce(BigDecimal.ZERO, BigDecimal::add);

		resultat.setNetAPayer(paieUtils.formaterBigDecimal(netPayer.subtract(SumCotiMultiSalaire)));

		return resultat;
	}
	
}
