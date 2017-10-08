package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.comportement.IDepEtAtt;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.personnages.comportement.attaque.AttaquePas;
import fr.ensma.ia.jeupersonnages.personnages.comportement.attaque.IAttaque;
import fr.ensma.ia.jeupersonnages.personnages.comportement.deplacement.IDeplacement;
import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;
import fr.ensma.ia.jeupersonnages.troupes.Troupe;

/**
 * Modélisation des éléments communs des personnages.
 * @author michaelrichard
 *
 */
public abstract class Personnage implements Cloneable, IDepEtAtt{

	private ECouleur couleur;
	private Float niveauVie;
	protected Troupe maTroupe;

	//Comportement de déplacement
	protected IDeplacement compDep;
	//Comportement d'attaque
	protected IAttaque compAtt;
	
	private static final Logger LOG = LogManager.getLogger(Personnage.class);
	
	/**
	 * Constructeur d'un personnage.
	 * @param coul ECouleur, la couleur
	 * @param nivie float, le niveau de vie
	 * @throws NullPointerException True si coul == null
	 */
	public Personnage(final ECouleur coul, final float nivie) throws NullPointerException{
		couleur = Objects.requireNonNull(coul);
		niveauVie = new Float(nivie);
		compDep = null;
		compAtt = new AttaquePas();
	}
	
	/**
	 * Obtient la couleur du personnage.
	 * 
	 * @return ECouleur, la couleur
	 */
	public final ECouleur getCouleur() {
		return couleur;
	}

	/**
	 * Modifie la couleur du personnage.
	 * 
	 * @param coul
	 *            ECouleur, la nouvelle couleur.
	 * @exception NullPointerException, True si coul == null.
	 */
	public final void setCouleur(final ECouleur coul) throws NullPointerException {
		couleur = Objects.requireNonNull(coul);
	}
	
	/**
	 * Obtient le niveau de vie du personnage.
	 * 
	 * @return float, le niveaud e vie
	 */
	public final float getNiveauVie() {
		return niveauVie.floatValue();
	}

	/**
	 * Modifie le niveau de vie du personnage.
	 * 
	 * @param nivie
	 *            float, le niveau de vie
	 */
	public final void setNiveauVie(final float nivie) {
		niveauVie = new Float(nivie);
	}

	@Override
	public void setMaTroupe(Troupe tr) {
		maTroupe = tr;
	}
	
	@Override
	public Troupe getMaTroupe() {
		return maTroupe;
	}
	
	/**
	 * Modifie le comportement de déplacement
	 * @param dep IDeplacement, le nouveau comportement de deplacement.
	 * @exception NullPointerException si dep == null.
	 */
	public void setCompDeplacement(IDeplacement dep){
		compDep = Objects.requireNonNull(dep);
	}
	
	public void setCompAtt(IAttaque att) {
		compAtt = Objects.requireNonNull(att);
	}
	
	@Override
	public final void deplacer(){
		compDep.deplacer();
	}
	
	@Override
	public void attaquer() throws CompAttaqueException {
		try{
		compAtt.attaquer();
		}catch(CompAttaqueException e){
			LOG.info("Et ben reste là espèce de pleutre !!!");
		}
	}
	
	@Override
	public abstract String toString();
	
	@Override
	public abstract Object clone() throws CloneNotSupportedException;
	
	
	/**
	 * Redéfinition de la méthode equals.
	 */
	@Override
	public boolean equals(Object obj) {
		//Inutile car classe abstraite.
		/*
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Personnage) 
		*/
		{
			Personnage pers = (Personnage)obj;
			if ((Objects.equals(couleur, pers.couleur)) && 
					(Objects.equals(niveauVie, pers.niveauVie))){
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
		int hsh = 3;
		final int fact = 5;
		hsh = hsh*fact + couleur.hashCode();
		hsh = hsh*fact + Float.floatToIntBits(niveauVie);
		return hsh;
	}
}
