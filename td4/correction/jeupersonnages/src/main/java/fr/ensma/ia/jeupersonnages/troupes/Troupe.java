package fr.ensma.ia.jeupersonnages.troupes;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.comportement.IDepEtAtt;
import fr.ensma.ia.jeupersonnages.outils.exceptions.TroupeVideException;
import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;

/**
 * Mod�lise une Troupe.
 * @author michaelrichard
 *
 */
public class Troupe {

	private String Id;
	private List<IDepEtAtt> lesOuvriers;
	private ListIterator<IDepEtAtt> listeIte;
	
	private static final Logger LOG = LogManager.getLogger(Troupe.class);

	/**
	 * Constructeur.
	 * @param id String, identifiant de la troupe.
	 * @param ouv Ouvrier, le premier ouvrier � ajouter � la troupe.
	 */
	public Troupe(String id, final IDepEtAtt ouv){
		Id = id;
		lesOuvriers = new ArrayList<IDepEtAtt>();
		ajoutPerso(ouv);
	}

	/**
	 * Ajout d'un ouvrier � la troupe.
	 * @param ouv Ouvrier, l'ouvrier � ajouter.
	 * @throws NullPointerException, True si ouv == null.
	 */
	public void ajoutPerso(final IDepEtAtt ouv) throws NullPointerException {
		ouv.setMaTroupe(this);
		lesOuvriers.add(Objects.requireNonNull(ouv));
	}

	/**
	 * Obtient le nombre de personnages dans la troupe.
	 * @return int, le nombre de personnages.
	 */
	public int nbPerso(){
		return lesOuvriers.size();
	}

	/**
	 * Test l'appartenance d'un personnage � la troupe. 
	 * @param ouv Ouvrier, le personnage � chercher
	 * @return boolean, true si dans la troupe, false sinon.
	 */
	public boolean contientPerso(final IDepEtAtt ouv){
		return lesOuvriers.contains(ouv);
	}
	
	/**
	 * Supprime un personnage de la troupe.
	 * @param ouv Ouvrier, le personnage � supprimer.
	 * @throws TroupeVideException True si la troupe est vide.
	 */
	public void supprPerso(final IDepEtAtt ouv) throws TroupeVideException{
		ouv.setMaTroupe(null);
		lesOuvriers.remove(ouv);
		if (nbPerso()==0){
			LOG.info("Troupe vide ... ");
			throw new TroupeVideException("Troupe Vide ...");
		}
	}
	
	/**
	 * Red�finition de la m�thode toString.
	 */
	@Override
	public String toString() {
		StringBuffer sort = new StringBuffer("La troupe " + Id + " :\n");
		listeIte = lesOuvriers.listIterator(0);
		while (listeIte.hasNext()){
			sort.append(listeIte.next());
			sort.append("\n-----\n");
		}
		return sort.toString();
	}
	
	/**
	 * Provoque le d�placement des �l�ments de la troupe.
	 */
	public void deplacement() {
		listeIte = lesOuvriers.listIterator(0);
		while (listeIte.hasNext()){
			listeIte.next().deplacer();
		}
	}
	
	/**
	 * Provoque l'attaque des �l�ments de la troupe.
	 */
	public void aLAttaque() {
		listeIte = lesOuvriers.listIterator(0);
		while (listeIte.hasNext()){
			try {
				listeIte.next().attaquer();
			} catch (CompAttaqueException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * Red�finition de la m�thode finalize.
	 */
	@Override
	protected void finalize() throws Throwable {
		LOG.info("La troupe a �t� d�cim�e !!");
	}
}
