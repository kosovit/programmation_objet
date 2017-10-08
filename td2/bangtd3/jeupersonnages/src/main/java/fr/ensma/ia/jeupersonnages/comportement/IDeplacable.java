package fr.ensma.ia.jeupersonnages.comportement;

import fr.ensma.ia.jeupersonnages.comportement.deplacement.IDeplacement;
import fr.ensma.ia.jeupersonnages.troupes.Troupe;

public interface IDeplacable extends IDeplacement { // une specialisation de mon deplacement 
	public void setMaTroupe(final Troupe tr);

}
