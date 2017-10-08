package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.comportement.attaque.AttaqueAvecCouteau;
import fr.ensma.ia.jeupersonnages.comportement.deplacement.EnCourant;
import fr.ensma.ia.jeupersonnages.comportement.deplacement.EnMarchant;
import fr.ensma.ia.jeupersonnages.option.IGuerrier;
import fr.ensma.ia.jeupersonnages.option.IGuerrier;
import fr.ensma.ia.jeupersonnages.option.OptionArmure;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.outils.exceptions.AttaqueException;
import fr.ensma.ia.jeupersonnages.personnages.etats.AuRepos;
import fr.ensma.ia.jeupersonnages.personnages.etats.*;


/**
 * Mod�lise un Guerrier.
 * @author michaelrichard
 *
 */
public class Guerrier extends PersonnageHumain implements IGuerrier{ //td6: on n'a pas de tt toucher cha sauf implements
	
	private static int numInstance;
	private Integer puissAtt;
	private PersonnageHumain maCible;

	
	private static final Logger LOG = LogManager.getLogger(Ouvrier.class);
	
	/**
	 * Obtient une instance de guerrier.
	 * @param coul ECouleur, la couleur.
	 * @param nivie float, le niveau de vie.
	 * @param patt int, la puissance d'attaque
	 * @throws NullPointerException True si coul == null
	 */
	public Guerrier(final ECouleur coul, final float nivie,
			final int patt) throws NullPointerException{
		super(("Guerrier"+numInstance++),coul,nivie);
		puissAtt = new Integer(patt);
		maCible=null;
		monDeplacement = new EnCourant();
		monArme = new AttaqueAvecCouteau();
	}
	
	/**
	 * Obtient une instance de guerrier.
	 * @param n String, le nom.
	 * @param coul ECouleur, la couleur.
	 * @param nivie float, le niveau de vie.
	 * @param patt int, la puissance d'attaque
	 * @throws NullPointerException True si n==null OR coul==null
	 */
	public Guerrier(final String n,final ECouleur coul, 
			final float nivie, final int patt)throws NullPointerException{
		super(n,coul,nivie);
		numInstance++;
		puissAtt = new Integer(patt);
		maCible=null;
		monDeplacement = new EnCourant();
		monArme = new AttaqueAvecCouteau();

	}
	
	/**
	 * Obtient la puissance d'attaque du guerrier.
	 * @return int, la puissance d'attaque.
	 */
	public final int getPuissanceAtt(){
		return puissAtt.intValue();
	}

	/**
	 * Modifie la puissance d'attaque du guerrier.
	 * @param puiss
	 */
	public final void setPuissanceAtt(final int puiss){
		puissAtt = new Integer(puiss);
	}

	
	
	
	/**
	 * D�finition de la cible d'attaque. 
	 * @param ci PersonnageHumain, la cible
	 * @param guer Guerrier, l'attaquant.
	 * @throws NullPointerException, True si ci == null.
	 */
	public void setCible(final PersonnageHumain ci) throws NullPointerException{
		maCible = Objects.requireNonNull(ci);
	}
	
	/**
	 * Provoque l'attaque du guerrier
	 */
	@Override
	public void attaquer() throws AttaqueException{
		// TODO Auto-generated method stub
		super.attaquer();
		maCible.tuVasMourrir(puissAtt, this);
	}
	
	/**
	 * 
	 */
	@Override
	protected void tuVasMourrir(final int puiss, final Guerrier guer) {
		setMonAttaquant(guer);
		maCible=guer;
		setNiveauVie(getNiveauVie()-(puiss/10f));
		LOG.info("AIIIIIEEEEEUUUUHHH ! ... nivVie : " + getNiveauVie());
		LOG.info(getNom() + " va se venger " + getMonAttaquant().getNom() + " !!!!");
	}
	
	/**
	 * Red�finition de la m�thode toString. Obtient une repr�sentation au format
	 * cha�ne d'un guerrier.
	 */
	@Override
	public String toString() {
		return getNom() + ", " + super.getCouleur() + ", " + super.getNiveauVie() +
				", " + puissAtt.intValue();
	}
	
	/**
	 * Red�finition de la m�thode equals.
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (obj instanceof Guerrier) {
			Guerrier guer = (Guerrier) obj;
			if ((super.equals((PersonnageHumain)guer)) && (puissAtt.equals(guer.puissAtt))) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Red�finition de la m�thode hashCode.
	 */
	@Override
	public int hashCode() {
		int hsh = 9;
		final int fact = 11;
		hsh = super.hashCode();
		hsh = hsh*fact + puissAtt;
		return hsh;
	}
	
	/**
	 * Red�finition de la m�thode clone.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Guerrier(getNom(), getCouleur(), getNiveauVie(), 
				puissAtt.intValue());
	}
	
	/**
	 * Red�finition du destructeur.
	 */
	@Override
	protected void finalize() throws Throwable {
		LOG.info("Destruction d'une instance de Guerrier");
	}



	
	
}
