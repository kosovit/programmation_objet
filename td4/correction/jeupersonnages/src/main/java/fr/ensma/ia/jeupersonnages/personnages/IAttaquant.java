package fr.ensma.ia.jeupersonnages.personnages;

import fr.ensma.ia.jeupersonnages.personnages.comportement.attaque.IAttaque;

public interface IAttaquant extends IAttaque {

	/**
	 * Obtient la puissance d'attaque du Mage.
	 * @return int, la puissance d'attaque.
	 */
	public int getPuissanceAtt();
	
	/**
	 * Modifie la puissance d'attaque du Mage.
	 * @param puiss
	 */
	void setPuissanceAtt(final int puiss);
	
	public void tuVasMourrir(final int puiss, final IAttaquant att);

}
