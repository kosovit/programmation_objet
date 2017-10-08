package fr.ensma.ia.jeupersonnages.personnages.etats;

import fr.ensma.ia.jeupersonnages.personnages.Personnage;
import fr.ensma.ia.jeupersonnages.personnages.etats.exception.EtatPersonnageException;

public class AuRepos extends AbsEtat {
		
	
	private Personnage pers ;
	public AuRepos(Personnage p) { //constructeur 
		pers = p ; // le this de personnage
	}

	@Override
	public void deplacer() {
		// TODO Auto-generated method stub
		pers.setEtatCourant(pers.getEnDeplacement());
		pers.deplacer();
	};

	@Override
	public void arret() throws EtatPersonnageException { // la personnage est deja en repos
		
	}

	@Override
	public void attaquer() throws EtatPersonnageException {
		
		
	}


	

}
