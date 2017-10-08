package fr.ensma.ia.jeupersonnages.personnages.comportement.attaque;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;

public class ALaHache implements IAttaque{

	private static final Logger LOG = LogManager.getLogger(ALaHache.class);
	
	@Override
	public void attaquer() throws CompAttaqueException {
		LOG.info("Ca va trancher mon lapin !!!! ");
	}
	
}
