package fr.ensma.ia.jeupersonnages.comportement.attaque;

import fr.ensma.ia.jeupersonnages.outils.exceptions.AttaqueException;

public interface IAttaquer {
	void attaquer() throws AttaqueException;
}
