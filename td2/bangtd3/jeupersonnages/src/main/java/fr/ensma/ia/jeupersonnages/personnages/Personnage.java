package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import fr.ensma.ia.jeupersonnages.comportement.IDepAtt;
import fr.ensma.ia.jeupersonnages.comportement.IDeplacable;
import fr.ensma.ia.jeupersonnages.comportement.attaque.IAttaquer;
import fr.ensma.ia.jeupersonnages.comportement.deplacement.IDeplacement;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.outils.exceptions.AttaqueException;
import fr.ensma.ia.jeupersonnages.personnages.etats.AuRepos;
import fr.ensma.ia.jeupersonnages.personnages.etats.EnDeplacement;
import fr.ensma.ia.jeupersonnages.personnages.etats.IEtat;
import fr.ensma.ia.jeupersonnages.troupes.Troupe;

/**
 * Modélisation des éléments communs des personnages.
 * @author michaelrichard
 *
 */
public abstract class Personnage implements Cloneable, IDepAtt{

	private ECouleur couleur;
	private Float niveauVie;
	protected Troupe maTroupe;
	protected IDeplacement monDeplacement;
	protected IAttaquer monArme;
	
	protected IEtat auRepos = new AuRepos(this);  // AuRepos(personnage)
	protected IEtat enDeplacement = new EnDeplacement(this); // das les methode au dep et  o va changer l'etat 
	protected IEtat etatCourant; //c'est propre a un personnage

	public void setEtatCourant(final IEtat e){etatCourant = e ;};
	
	public IEtat getAuRepos() {return auRepos;};
	public IEtat getEnDeplacement() {return enDeplacement;};
	
	
	
	public void SetMonDeplacement(IDeplacement dep){
		monDeplacement = dep;
	};
	
	
	public void SetMonArme(IAttaquer arm){
		monArme = arm;
	};
	/**
	 * Constructeur d'un personnage.
	 * @param coul ECouleur, la couleur
	 * @param nivie float, le niveau de vie
	 * @throws NullPointerException True si coul == null
	 */
	public Personnage(final ECouleur coul, final float nivie) throws NullPointerException{
		couleur = Objects.requireNonNull(coul);
		niveauVie = new Float(nivie);
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
	public abstract String toString();
	
	
	
	@Override
	public abstract Object clone() throws CloneNotSupportedException;
	
	
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
		if (obj instanceof Personnage) {
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
	@Override
	public void deplacer() {
		monDeplacement.deplacer();
		
	}
	@Override
	public void attaquer() throws AttaqueException {
		try {
			monArme.attaquer();
		} catch (AttaqueException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
