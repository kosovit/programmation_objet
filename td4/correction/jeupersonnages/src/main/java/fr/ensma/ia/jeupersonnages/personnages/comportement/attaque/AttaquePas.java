package fr.ensma.ia.jeupersonnages.personnages.comportement.attaque;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;

public class AttaquePas implements IAttaque {

	private static final Logger LOG = LogManager.getLogger(AttaquePas.class);
	
	@Override
	public void attaquer() throws CompAttaqueException {
		LOG.error("J'attaque pas moi !!!! ");
		throw new CompAttaqueException("Ce personnage ne peut attaquer ... ");
	}
	
}
