package fr.ensma.ia.jeupersonnages.personnages.comportement.attaque;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;

public class AuCouteau implements IAttaque {

private static final Logger LOG = LogManager.getLogger(AuCouteau.class);
	
	@Override
	public void attaquer() throws CompAttaqueException {
		LOG.info("Je vais te transpercer avec mon petit canif !!!! ");
	}
	
}
