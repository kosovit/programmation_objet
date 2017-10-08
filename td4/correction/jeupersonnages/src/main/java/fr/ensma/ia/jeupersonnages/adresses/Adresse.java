package fr.ensma.ia.jeupersonnages.adresses;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Modélise une adresse de personnage. Par : - un nom de village - un numéro de
 * hutte
 * 
 * @author michaelrichard
 *
 */
public class Adresse implements Cloneable{

	private static final Logger LOG = LogManager.getLogger(Adresse.class);
	private Integer numHutte;
	private String nomVillage;

	/**
	 * Obtient une Instance d'adresse.
	 * 
	 * @param num
	 *            int, le numéro de la hutte
	 * @param nomv
	 *            String, le nom du village
	 * @exception NullPointerException,
	 *                True si nomv = null
	 */
	public Adresse(final int num, final String nomv) throws NullPointerException {
		numHutte = new Integer(num);
		nomVillage = Objects.requireNonNull(nomv);
	}

	/**
	 * Obtient le numéro de la hutte.
	 * 
	 * @return int, le numéro de la hutte
	 */
	public final int getNumHutte() {
		return numHutte;
	}

	/**
	 * Modifie le numéro de la hutte.
	 * 
	 * @param num
	 *            int, le nouveau numéro de hutte
	 */
	public final void setNumHutte(final int num) {
		numHutte = new Integer(num);
	}

	/**
	 * Obtient le nom du village.
	 * 
	 * @return String, le nom du village
	 */
	public final String getNomVillage() {
		return nomVillage;
	}

	/**
	 * Modifie le nom du village.
	 * 
	 * @param nomv
	 *            String, le nouveau nom du village.
	 * @exception NullPointerException,
	 *                True si nomv = null
	 */
	public final void setNomVillage(final String nomv) throws NullPointerException {
		nomVillage = Objects.requireNonNull(nomv, "nomv ne peut-etre null.");
	}

	/**
	 * Redéfinition de la méthode toString. Obtient une représentation au format
	 * Chaîne d'une adresse sous la forme : "Hutte numéro numHutte, nomVillage"
	 */
	@Override
	public String toString() {
		return "Hutte numéro " + numHutte + ", " + nomVillage;
	}

	/**
	 * Redéfinition de la méthode equals. Vrai si numéro hutte et nom de village
	 * identique
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Adresse) {
			Adresse ad = (Adresse) obj;
			if ((Objects.equals(numHutte, ad.numHutte)) 
					&& (Objects.equals(nomVillage, ad.nomVillage))) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Redéfinition de la méthode hashcode homogène à la redéfinition de equals.
	 */
	@Override
	public int hashCode() {
		int hsh = 7;
		final int fact = 13;
		hsh = hsh * fact + numHutte.intValue();
		hsh = hsh * fact + nomVillage.hashCode();
		return hsh;
	}

	/**
	 * Redéfinition de la méthode clone. Obtient une copie de l'instance.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		Adresse ad = new Adresse(new Integer(numHutte.intValue()), nomVillage.toString());
		return ad;
	}

	/**
	 * Redéfinition du destructeur.
	 */
	@Override
	protected void finalize() throws Throwable {
		LOG.info("Destruction d'une instance d'Adresse ...");
		super.finalize();
	}
}
