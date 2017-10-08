package fr.ensma.ia.jeupersonnages.personnages.comportement.deplacement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class EnVolant implements IDeplacement {
	
	private static final Logger LOG = LogManager.getLogger(EnVolant.class);
	
	@Override
	public void deplacer() {
		LOG.info("je me déplace en volant ...");
	}
}
