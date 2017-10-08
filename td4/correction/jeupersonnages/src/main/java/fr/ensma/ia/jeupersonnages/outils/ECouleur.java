package fr.ensma.ia.jeupersonnages.outils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.outils.exceptions.ECouleurException;

/**
 * D�finition d'un Type ECouleur : rouge, jaune, bleu, vert
 * 
 * @author michaelrichard
 *
 */
public enum ECouleur {
	rouge(1), jaune(2), bleu(3), vert(4);

	private int valIndex;
	
	private static final Logger LOG = LogManager.getLogger(ECouleur.class);

	/**
	 * Constructeur priv� permettant d'associer une valeur au litt�ral de
	 * l'�numeration.
	 * 
	 * @param indx
	 *            int, la valeur à associer.
	 */
	private ECouleur(final int indx) {
		valIndex = indx;
	}

	/**
	 * Obtient l'index correspondant � la ECouleur
	 * 
	 * @param coul
	 *            ECouleur, la couleur
	 * @return int, l'index correspondant.
	 */
	public int getIndex() {
		return valIndex;
	}

	/**
	 * Obtient la ECouleur correspondant � l'index
	 * 
	 * @param indx
	 *            int, l'index
	 * @return ECouleur, la couleur correspondante
	 * @throws ECouleurException
	 *             si pas de correspondance d'index
	 * @see ECouleurException
	 */
	public static ECouleur getColor(final int indx) throws ECouleurException {
		switch (indx) {
		case 1:
			return rouge;
		case 2:
			return jaune;
		case 3:
			return bleu;
		case 4:
			return vert;
		default:
			LOG.error("Valeur non attendue ...");
			throw new ECouleurException("Index sans correspondance ...");
		}
	}
}
