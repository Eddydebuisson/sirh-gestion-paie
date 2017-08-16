package dev.paie.config.aspect;

import java.time.LocalDateTime;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import dev.paie.entite.Performance;
import dev.paie.repository.PerformanceRepository;

@Configuration
@Aspect
public class ControllerPerformanceAspect {
	private static final Logger LOGGER = LoggerFactory.getLogger(ControllerPerformanceAspect.class);

	@Autowired
	private PerformanceRepository performanceRepository;


	@Around("execution(* dev.paie.web.controller.*.*(..))")
	public Object logPerf(ProceedingJoinPoint pjp) throws Throwable {
		LOGGER.debug("Début d'exécution de la méthode " + pjp.getSignature().toString());
		long debut = System.currentTimeMillis();
		String name = pjp.getSignature().getName();

		Object resultat = pjp.proceed();
		LOGGER.debug("Fin d'exécution de la méthode");
		long fin = System.currentTimeMillis();

		long excution = fin - debut;
		LocalDateTime date = LocalDateTime.now();
		performanceRepository.save(new Performance(name, date, excution));

		return resultat;
	}
}