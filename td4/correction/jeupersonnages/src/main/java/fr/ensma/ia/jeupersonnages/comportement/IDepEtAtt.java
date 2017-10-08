package fr.ensma.ia.jeupersonnages.comportement;

import fr.ensma.ia.jeupersonnages.personnages.comportement.attaque.IAttaque;
import fr.ensma.ia.jeupersonnages.personnages.comportement.deplacement.IDeplacement;
import fr.ensma.ia.jeupersonnages.troupes.Troupe;

public interface IDepEtAtt extends IAffichable,IDeplacement, IAttaque{

	/**
	 * D�clenchement d'un d�placement.
	 */
	public void setMaTroupe(final Troupe tr);
	public Troupe getMaTroupe();
}
