package dev.paie.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@EnableTransactionManagement
@Service
public class CotisationServiceJpa implements CotisationService {
	@PersistenceContext
	private EntityManager em;

	@Transactional
	@Override
	public void sauvegarder(Cotisation nouvelleCotisation) {
		em.persist(nouvelleCotisation);
	}

	@Transactional
	@Override
	public void mettreAJour(Cotisation cotisation) {
		em.merge(cotisation);
	}

	@Override
	public List<Cotisation> lister() {
		List<Cotisation> listeCotisation = new ArrayList<>();
		TypedQuery<Cotisation> query = em.createQuery("select v from Cotisation v ", Cotisation.class);
		listeCotisation = query.getResultList();
		return listeCotisation;
	}
}