package fr.ensma.ia.jeupersonnages.personnages.comportement.attaque;

import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;

public interface IAttaque {

	/**
	 * Provoque l'attaque du guerrier
	 */
	public void attaquer() throws CompAttaqueException;
	
}
