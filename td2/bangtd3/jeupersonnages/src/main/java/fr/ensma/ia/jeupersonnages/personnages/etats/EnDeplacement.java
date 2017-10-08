package fr.ensma.ia.jeupersonnages.personnages.etats;

import fr.ensma.ia.jeupersonnages.personnages.Personnage;
import fr.ensma.ia.jeupersonnages.personnages.etats.exception.EtatPersonnageException;

public class EnDeplacement extends AbsEtat {
	private Personnage pers ;
	public EnDeplacement(final Personnage P) { pers = P ;}


		
	

	@Override
	public void arret() {
		pers.setEtatCourant(pers.getAuRepos());
		//pers.();	
	}

}
