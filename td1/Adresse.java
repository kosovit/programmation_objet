package jeupersonnagesv1.adresses;

/**
 * Modélise une adresse de personnage.
 * par :
 *	- un nom de village
 *	- un numéro de hutte
 * @author michaelrichard
 *
 */
public class Adresse implements Cloneable{

	private Integer numHutte;
	private String nomVillage;

	/**
	 * Obtient une Instance d'adresse.
	 * @param num Integer, le numéro de la hutte
	 * @param nomv String, le nom du village
	 */
	public Adresse(final Integer num, final String nomv){
		numHutte = num;
		nomVillage = nomv;
	}

	/**
	 * Obtient le numéro de la hutte.
	 * @return Integer, le numéro de la hutte
	 */
	public final Integer getNumHutte(){
		return numHutte;
	}

	/**
	 * Modifie le numéro de la hutte.
	 * @param num Integer, le nouveau numéro de hutte
	 */
	public final void setNumHutte(final Integer num){
		numHutte = num;
	}

	/**
	 * Obtient le nom du village.
	 * @return String, le nom du village
	 */
	public final String getNomVillage(){
		return nomVillage;
	}

	/**
	 * Modifie le nom du village.
	 * @param nomv String, le nouveau nom du village.
	 */
	public final void setNomVillage(final String nomv){
		nomVillage = nomv;
	}

	/**
	 * Redéfinition de la méthode toString.
	 * Obtient une représentation au format chaine d'une adresse sous la forme :
	 * "Hutte numéro numHutte, nomVillage"
	 */
	@Override
	public String toString() {
		return "Hutte numéro " + numHutte + ", "
				+ nomVillage;
	}
	
	
	/**
	 * Redéfinition de la méthode equals.
	 * Vrai si numéro hutte et nom de village identique
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
			Adresse ad = (Adresse)obj;
			if ((numHutte.equals(ad.numHutte)) &&
					(nomVillage.compareTo(ad.nomVillage)==0)){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Redéfinition du destructeur.
	 */
	@Override
	protected void finalize() throws Throwable {
		System.out.println("Au revoir l'adresse ...");
		super.finalize();
	}
}
