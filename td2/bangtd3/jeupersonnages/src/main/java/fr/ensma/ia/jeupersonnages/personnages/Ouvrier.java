package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test.None;

import fr.ensma.ia.jeupersonnages.adresses.Adresse;
import fr.ensma.ia.jeupersonnages.comportement.attaque.AttaqueAvecCouteau;
import fr.ensma.ia.jeupersonnages.comportement.attaque.AttaquePas;
import fr.ensma.ia.jeupersonnages.comportement.deplacement.*;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.troupes.Troupe;

/**
 * Modélisation d'un ouvrier.
 * 
 * @author michaelrichard
 * @see Adresse
 */
public class Ouvrier extends PersonnageHumain {

	private static int numInstance;
	
	private Integer puissance;
	private Adresse monAdresse;
	
	private static final Logger LOG = LogManager.getLogger(Ouvrier.class);

	/**
	 * Obtient une instance d'Ouvrier avec un nom par défaut.
	 * 
	 * @param coul
	 *            ECouleur, sa couleur
	 * @param nivie
	 *            float, son niveau de vie
	 * @param puiss
	 *            int, sa puissance
	 * @param ad
	 *            Adresse, l'adresse de l'ouvrier
	 * @exception NullPointerException, True si coul == null
	 * @see Adresse
	 */
	public Ouvrier(final ECouleur coul, final float nivie, final int puiss,
			final Adresse ad) throws NullPointerException{
		super(("Ouvrier"+numInstance++),coul,nivie);
		puissance = new Integer(puiss);
		monAdresse = ad;
		monArme = new AttaquePas();
		monDeplacement = new EnMarchant();

	}

	/**
	 * Obtient une instance d'Ouvrier.
	 * 
	 * @param n
	 *            String, son nom
	 * @param coul
	 *            ECouleur, sa couleur
	 * @param nivie
	 *            float, son niveau de vie
	 * @param puiss
	 *            int, sa puissance * @param ad Adresse, l'adresse de l'ouvrier
	 * @exception NullPointerException, True si n==null OR coul==null
	 * @see Adresse
	 */
	public Ouvrier(final String n, final ECouleur coul, final float nivie, final int puiss,
			final Adresse ad) throws NullPointerException{
		super(n,coul,nivie);
		numInstance++;
		puissance = new Integer(puiss);
		monAdresse = ad;
		monArme = new AttaquePas();

		monDeplacement = new EnMarchant();
		//monArme = None;
	}

	/**
	 * Obtient la puissance de l'ouvrier.
	 * 
	 * @return int, la puissance
	 */
	public final int getPuissance() {
		return puissance.intValue();
	}

	/**
	 * Modifie la puissance de l'ouvrier.
	 * 
	 * @param puiss
	 */
	public final void setPuissance(final int puiss) {
		puissance = new Integer(puiss);
	}

	/**
	 * Obtient l'adresse de l'ouvrier.
	 * 
	 * @return Adresse, l'adresse.
	 */
	public final Adresse getMonAdresse() {
		return monAdresse;
	}

	/**
	 * Modifie l'adresse de l'ouvrier.
	 * 
	 * @param ad
	 *            Adresse, la nouvelle adresse.
	 */
	public final void setMonAdresse(final Adresse ad) {
		monAdresse = ad;
	}

	
	
	@Override
	public void tuVasMourrir(final int puiss, final Guerrier guer) {
		setMonAttaquant(guer);
		setNiveauVie(getNiveauVie()-(puiss/10f));
		LOG.info("AIIIIIEEEEEUUUUHHH ! Tu fais mal " + getMonAttaquant().getNom()
					+ "... nivVie : " + getNiveauVie());
	}
	
	/**
	 * Redéfinition de la méthode toString. Obtient une représentation au format
	 * chaîne d'un ouvrier.
	 */
	@Override
	public String toString() {
		return getNom() + ", " + super.getCouleur() + ", " + super.getNiveauVie() + ", " + puissance.intValue() + "\n"
				+ Objects.toString(monAdresse, "ad : ????");
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
		if (obj instanceof Ouvrier) {
			Ouvrier ouv = (Ouvrier) obj;
			if ((super.equals((PersonnageHumain)ouv)) && (puissance.equals(ouv.puissance))
					&& Objects.equals(monAdresse,ouv.monAdresse)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Redéfinition de la méthode hashCode.
	 */
	@Override
	public int hashCode() {
		int hsh = 5;
		final int fact = 11;
		hsh = super.hashCode();
		hsh = hsh*fact + puissance;
		hsh = hsh*fact + (monAdresse == null?0:monAdresse.hashCode());
		return hsh;
	}

	/**
	 * Redéfinition de la méthode clone.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Ouvrier(getNom(), getCouleur(), getNiveauVie(), 
				puissance.intValue(), (monAdresse == null?null:(Adresse)monAdresse.clone()));
	}
	
	@Override
	protected void finalize() throws Throwable {
		LOG.info("Destruction d'une instance d'Ouvrier");
	}



	

	

	
}
