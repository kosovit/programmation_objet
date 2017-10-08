package fr.ensma.ia.jeupersonnages.troupes;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.comportement.IDepAtt;
import fr.ensma.ia.jeupersonnages.comportement.IDeplacable;
import fr.ensma.ia.jeupersonnages.comportement.attaque.*;
import fr.ensma.ia.jeupersonnages.outils.exceptions.AttaqueException;
import fr.ensma.ia.jeupersonnages.outils.exceptions.TroupeVideException;

/**
 * Modélise une Troupe.
 * @author michaelrichard
 *
 */
public class Troupe {

	private String Id;
	private List<IDepAtt> lesOuvriers;
	private ListIterator<IDepAtt> listeIte;
	
	private static final Logger LOG = LogManager.getLogger(Troupe.class);

	/**
	 * Constructeur.
	 * @param id String, identifiant de la troupe.
	 * @param ouv Ouvrier, le premier ouvrier à ajouter à la troupe.
	 */
	public Troupe(String id, final IDeplacable ouv){
		Id = id;
		lesOuvriers = new ArrayList<IDepAtt>();
		ajoutPerso(ouv);
	}

	/**
	 * Ajout d'un ouvrier à la troupe.
	 * @param ouv Ouvrier, l'ouvrier à ajouter.
	 * @throws NullPointerException, True si ouv == null.
	 */
	public void ajoutPerso(final IDeplacable ouv) throws NullPointerException {
		ouv.setMaTroupe(this);
		lesOuvriers.add((IDepAtt) Objects.requireNonNull(ouv));
	}

	/**
	 * Obtient le nombre de personnages dans la troupe.
	 * @return int, le nombre de personnages.
	 */
	public int nbPerso(){
		return lesOuvriers.size();
	}

	/**
	 * Test l'appartenance d'un personnage à la troupe. 
	 * @param ouv Ouvrier, le personnage à chercher
	 * @return boolean, true si dans la troupe, false sinon.
	 */
	public boolean contientPerso(final IDepAtt ouv){
		return lesOuvriers.contains(ouv);
	}
	
	/**
	 * Supprime un personnage de la troupe.
	 * @param ouv Ouvrier, le personnage à supprimer.
	 * @throws TroupeVideException True si la troupe est vide.
	 */
	public void supprPerso(final IDepAtt ouv) throws TroupeVideException{
		ouv.setMaTroupe(null);
		lesOuvriers.remove(ouv);
		if (nbPerso()==0){
			LOG.info("Troupe vide ... ");
			throw new TroupeVideException("Troupe Vide ...");
		}
	}
	
	/**
	 * Redéfinition de la méthode toString.
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
	 * Provoque le déplacement des éléments de la troupe.
	 */
	public void deplacement() {
		listeIte = lesOuvriers.listIterator(0);
		while (listeIte.hasNext()){
			listeIte.next().deplacer();
		}
	}
	/**
	 * Provoque l'entrée en attaque des éléments de la troupe.
	 */
	public void attaque() throws AttaqueException{
		listeIte = lesOuvriers.listIterator(0);
		while (listeIte.hasNext()){
			listeIte.next().attaquer();
		}
	}
	
	/**
	 * Redéfinition de la méthode finalize.
	 */
	@Override
	protected void finalize() throws Throwable {
		LOG.info("La troupe a été décimée !!");
	}
}
