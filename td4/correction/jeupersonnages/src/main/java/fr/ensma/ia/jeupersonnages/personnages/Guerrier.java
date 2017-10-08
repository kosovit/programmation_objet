package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.personnages.comportement.attaque.AuCouteau;
import fr.ensma.ia.jeupersonnages.personnages.comportement.deplacement.EnMarchant;
import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;

/**
 * Modélise un Guerrier.
 * @author michaelrichard
 *
 */
public class Guerrier extends PersonnageHumain implements IAttaquant{
	
	private static int numInstance;
	private Integer puissAtt;
	private PersonnageHumain maCible;
	
	private static final Logger LOG = LogManager.getLogger(Guerrier.class);
	
	private void initValue(final int patt){
		puissAtt = new Integer(patt);
		maCible=null;
		compDep = new EnMarchant();
		compAtt = new AuCouteau();
	}
	
	/**
	 * Obtient une instance de guerrier.
	 * @param coul ECouleur, la couleur.
	 * @param nivie float, le niveau de vie.
	 * @param patt int, la puissance d'attaque
	 * @throws NullPointerException True si coul == null
	 */
	public Guerrier(final ECouleur coul, final float nivie,
			final int patt) throws NullPointerException{
		super(("Guerrier"+ ++numInstance),coul,nivie);
		initValue(patt);
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
		initValue(patt);
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
	 * Définition de la cible d'attaque. 
	 * @param ci PersonnageHumain, la cible
	 * @throws NullPointerException, True si ci == null.
	 */
	public void setCible(final PersonnageHumain ci) throws NullPointerException{
		maCible = Objects.requireNonNull(ci);
	}
	
	@Override
	public void attaquer() throws CompAttaqueException {
		compAtt.attaquer();
		maCible.tuVasMourrir(puissAtt, this);
	}
	
	/**
	 * 
	 */
	@Override
	public void tuVasMourrir(final int puiss, final IAttaquant att) {
		setMonAttaquant(att);
		maCible=(PersonnageHumain)att;
		setNiveauVie(getNiveauVie()-(puiss/10f));
		LOG.info("AIIIIIEEEEEUUUUHHH ! ... nivVie : " + getNiveauVie());
		LOG.info(getNom() + " va se venger " + ((PersonnageHumain)getMonAttaquant()).getNom() + " !!!!");
	}
	
	/**
	 * Redéfinition de la méthode toString. Obtient une représentation au format
	 * chaîne d'un guerrier.
	 */
	@Override
	public String toString() {
		return getNom() + ", " + super.getCouleur() + ", " + super.getNiveauVie() +
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
		if (obj instanceof Guerrier) {
			Guerrier guer = (Guerrier) obj;
			if ((super.equals((PersonnageHumain)guer)) && (puissAtt.equals(guer.puissAtt))) {
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
		int hsh = 9;
		final int fact = 11;
		hsh = super.hashCode();
		hsh = hsh*fact + puissAtt;
		return hsh;
	}
	
	/**
	 * Redéfinition de la méthode clone.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Guerrier(getNom(), getCouleur(), getNiveauVie(), 
				puissAtt.intValue());
	}
	
	/**
	 * Redéfinition du destructeur.
	 */
	@Override
	protected void finalize() throws Throwable {
		LOG.info("Destruction d'une instance de Guerrier");
	}
	
}
