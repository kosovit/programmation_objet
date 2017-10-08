package fr.ensma.ia.jeupersonnages.option;

import fr.ensma.ia.jeupersonnages.personnages.Guerrier;

public abstract class   OptionAbstrait implements IGuerrier {
	protected IGuerrier guer;
	protected int dimAtt;

	public OptionAbstrait(IGuerrier g) {
		this.guer = g;
		this.dimAtt = 1;
		
	}
	// ici ovveride tt les overiide de Iguerrier
	//set nivaude vie = getnv - nvie/dimAtt

	
	
	
	
	

}
