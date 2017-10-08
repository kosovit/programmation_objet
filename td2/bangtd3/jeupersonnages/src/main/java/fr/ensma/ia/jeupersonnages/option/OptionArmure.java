package fr.ensma.ia.jeupersonnages.option;

import fr.ensma.ia.jeupersonnages.personnages.Guerrier;
import fr.ensma.ia.jeupersonnages.personnages.Personnage;

public class OptionArmure extends OptionAbstrait {
	private IGuerrier pers ;  // Iguerrier !! yes !! 
	
	// le log ici
	
	public OptionArmure (IGuerrier p) { //constructeur 
		super(p);
		dimAtt  = 2 ;
		pers = p ; // le this de guerrier
		
		
	}

	@Override
	public String getNom() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getPuissanceAtt() {
		// TODO Auto-generated method stub
		return (pers.getPuissanceAtt()/ 2);
	};

}
