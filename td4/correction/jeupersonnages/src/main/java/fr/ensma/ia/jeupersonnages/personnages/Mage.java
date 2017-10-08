package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.personnages.comportement.deplacement.EnMarchant;

public class Mage extends PersonnageHumain implements IAttaquant {
	
	private static int numInstance=0;
	private Integer puissAtt;
	private Integer nivSort;
	private PersonnageHumain maCible;
		
	private static final Logger LOG = LogManager.getLogger(Mage.class);
	
	private void initValue(final int patt, final int nsort){
		puissAtt = new Integer(patt);
		nivSort = nsort;
		maCible=null;
		compDep = new EnMarchant();
	}
	
	
	public Mage (final String n, final ECouleur coul, final float nivie,
			final int patt, final int nsort){
		super(n,coul,nivie);
		numInstance++;
		initValue(patt, nsort);
	}
	
	public Mage (final ECouleur coul, final float nivie,
			final int patt, final int nsort){
		super("Mage" + ++numInstance,coul,nivie);
		initValue(patt, nsort);
	}
	
	@Override
	public int getPuissanceAtt() {
		return puissAtt;
	}
	
	@Override
	public void setPuissanceAtt(final int puiss){
		puissAtt = new Integer(puiss);
	}
	
	/**
	 * Obtient le niveau de sort du mage.
	 * @return int, le niveau de sort.
	 */
	public final int getNiveauSort(){
		return nivSort.intValue();
	}
	
	/**
	 * Modifie le niveau de sortilège du Mage.
	 * @param nsort, int le nouveau niveau de sortilège.
	 */
	public final void setNiveauSort(final int nsort){
		nivSort = new Integer(nsort);
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
	public void attaquer() {
		LOG.info(this.getNom() + " dit : A l'attaque !!!!" );
		maCible.tuVasMourrir(puissAtt,this);
	}
	
	@Override
	public void tuVasMourrir(int puiss, IAttaquant att) {
		setMonAttaquant(att);
		//maCible = (PersonnageHumain)att;
		setNiveauVie(getNiveauVie()-(puiss/10f));
		LOG.info("AIIIIIEEEEEUUUUHHH ! ... nivVie : " + getNiveauVie());
		LOG.info(this.getNom() + " va se venger " + ((PersonnageHumain)getMonAttaquant()).getNom() + " !!!!");
	}

	/**
	 * Redéfinition de la méthode toString
	 */
	@Override
	public String toString() {
		return getNom() + ", " + getCouleur() + ", " + getNiveauVie() +
				", " + puissAtt.intValue() + ", " + nivSort.intValue();
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
		if (obj instanceof Mage) {
			Mage mge = (Mage) obj;
			if ((super.equals((PersonnageHumain)mge)) && 
					(puissAtt.equals(mge.puissAtt))
					&& (nivSort.equals(mge.nivSort))) {
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
		hsh = hsh*fact + puissAtt.intValue();
		hsh = hsh*fact + nivSort.intValue();
		return hsh;
	}
	
	/**
	 * Redéfinition de la méthode clone.
	 */
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new Mage(getNom(), getCouleur(), getNiveauVie(),
				puissAtt.intValue(), nivSort.intValue());
	}
}
