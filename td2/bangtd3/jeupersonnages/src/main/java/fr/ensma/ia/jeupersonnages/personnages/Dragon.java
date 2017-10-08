package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.comportement.attaque.AttaqueAvecCouteau;
import fr.ensma.ia.jeupersonnages.comportement.attaque.AttaqueAvecSort;
import fr.ensma.ia.jeupersonnages.comportement.deplacement.EnCourant;
import fr.ensma.ia.jeupersonnages.comportement.deplacement.EnVolant;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;

/**
 * Modélise un Dragon.
 * @author michaelrichard
 *
 */
public class Dragon extends Personnage{

	private Integer puissAtt;
	
	private static final Logger LOG = LogManager.getLogger(Ouvrier.class);

	/**
	 * Obtient une instance de Dragon.
	 * @param coul ECouleur, la couleur.
	 * @param nivie float, le niveau de vie.
	 * @param puiss int, la puissance d'attaque.
	 * @exception NullPointerException, True si coul == null.
	 */
	public Dragon(final ECouleur coul, final float nivie, 
			final int puiss) throws NullPointerException{
		super(coul,nivie);
		puissAtt = new Integer(puiss);
		monDeplacement = new EnVolant();
		monArme = new AttaqueAvecSort();
	}
	
	/**
	 * Obtient la puissance d'attaque du dragon.
	 * @return int, la puissance d'attaque.
	 */
	public final int getPuissanceAtt(){
		return puissAtt.intValue();
	}

	/**
	 * Modifie la puissance d'attaque du dragon.
	 * @param puiss
	 */
	public final void setPuissanceAtt(final int puiss){
		puissAtt = new Integer(puiss);
	}
	
	/**
	 * Provoque le déplacement du dragon en volant.
	 */
	
	
	/**
	 * Redéfinition de la méthode toString.
	 */
	@Override
	public String toString() {
		return "Dragon, " + getCouleur().toString() + ", " + getNiveauVie() +
				", " + puissAtt.intValue();
	}
	
	/**
	 * Redéfinition de la méthode equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Dragon) {
			Dragon drag = (Dragon)obj;
			if((super.equals((Personnage)drag)) && (Objects.equals(puissAtt, drag.puissAtt))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Redéfinition de la méthode hashcode.
	 */
	@Override
	public int hashCode() {
		int hsh = 7;
		final int fact = 11;
		hsh = super.hashCode();
		hsh = hsh*fact + puissAtt.intValue();
		return hsh;
	}
	
	/**
	 * Redéfinition de la méthode clone.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Dragon(getCouleur(), getNiveauVie(), puissAtt.intValue());
	}
	
	/**
	 * Redéfinition du destructeur.
	 */
	@Override
	protected void finalize() throws Throwable {
		LOG.info("Destruction d'une instance de Dragon ...");
	}

		
}
