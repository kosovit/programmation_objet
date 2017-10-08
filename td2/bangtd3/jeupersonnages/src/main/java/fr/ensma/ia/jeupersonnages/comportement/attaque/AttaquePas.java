package fr.ensma.ia.jeupersonnages.comportement.attaque;
import fr.ensma.ia.jeupersonnages.comportement.attaque.IAttaquer;
import fr.ensma.ia.jeupersonnages.outils.exceptions.AttaqueException;

public class AttaquePas implements IAttaquer{

	@Override
	public void attaquer() throws AttaqueException{
		System.out.println("j'attaque pas ! ");

		
	}
	

}
