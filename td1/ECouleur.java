package jeupersonnagesv1.outils;

import jeupersonnagesv1.outils.exceptions.ECouleurException;

/**
 * Définition d'un Type ECouleur : rouge, jaune, bleu, vert
 * @author michaelrichard
 *
 */
public enum ECouleur {
	rouge(1),jaune(2),bleu(3),vert(4);
	
	private int valIndex;

	/**
	 * Constructeur privé permettant d'associer une valeur au littéral de l'énumeration.
	 * @param indx int, la valeur à associer.
	 */
	private ECouleur(final int indx){
		valIndex = indx;
	}

	/**
	 * Obtient l'index correspondant à la ECouleur
	 * @param coul ECouleur, la couleur
	 * @return int, l'index correspondant.
	 */
	public int getIndex(){
		return valIndex;
	}
	
	/**
	 * Obtient la ECouleur correspondant à l'index 
	 * @param indx int, l'index
	 * @return ECouleur, la couleur correpondante
	 * @throws ECouleurException si pas de correspondance d'index
	 * @see ECouleurException
	 */
	public static ECouleur getColor(final int indx) throws ECouleurException {
		switch (indx) {
		case 1: 
			return rouge;
		case 2: 
			return jaune;
		case 3 : 
			return bleu;
		case 4 : 
			return vert;
		default: 
			throw new ECouleurException("Index sans correspondance ...");
		}
	}
}
