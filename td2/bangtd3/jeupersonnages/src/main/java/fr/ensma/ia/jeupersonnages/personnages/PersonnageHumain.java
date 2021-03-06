package fr.ensma.ia.jeupersonnages.personnages;

import java.util.Objects;

import fr.ensma.ia.jeupersonnages.outils.ECouleur;

/**
 * Mod�lisation des �l�ments communs des personnages humains.
 * @author michaelrichard
 *
 */
public abstract class PersonnageHumain extends Personnage {
	
	private String nom;
	private Guerrier monAttaquant;
	
	public PersonnageHumain(final String n, final ECouleur coul, 
			final float nivie) throws NullPointerException{
		super(coul,nivie);
		nom = Objects.requireNonNull(n);
		monAttaquant = null;
	}
	
	/**
	 * Obtient le nom du personnage.
	 * 
	 * @return String, le nom
	 */
	public final String getNom() {
		return nom;
	}
		
	protected final void setMonAttaquant(Guerrier guer)throws NullPointerException{
		monAttaquant = Objects.requireNonNull(guer);
	}
	
	protected final Guerrier getMonAttaquant(){
		return monAttaquant;
	}
	
	/**
	 * Provoque le deplacement d'un personnage humain.
	 */
	//public abstract void deplacer();
	
	
	/**
	 * Attaque d'un personnage humain.
	 */
	protected abstract void tuVasMourrir(final int puiss, final Guerrier guer);
	
	
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
		if (obj instanceof PersonnageHumain) {
			PersonnageHumain pers = (PersonnageHumain)obj;
			if((super.equals((Personnage)pers)) && (nom.compareTo(pers.nom) == 0)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Red�finition de la m�thode hashcode.
	 */
	@Override
	public int hashCode() {
		int hsh = 5;
		final int fact = 13;
		hsh = super.hashCode();
		hsh = hsh*fact + (nom.hashCode());
		return hsh;
	}
}
