package fr.ensma.ia.jeupersonnages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import fr.ensma.ia.jeupersonnages.adresses.Adresse;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.outils.exceptions.ECouleurException;
import fr.ensma.ia.jeupersonnages.outils.exceptions.TroupeVideException;
import fr.ensma.ia.jeupersonnages.personnages.Dragon;
import fr.ensma.ia.jeupersonnages.personnages.Guerrier;
import fr.ensma.ia.jeupersonnages.personnages.Ouvrier;
import fr.ensma.ia.jeupersonnages.personnages.comportement.attaque.ALaHache;
import fr.ensma.ia.jeupersonnages.personnages.comportement.exceptions.CompAttaqueException;
import fr.ensma.ia.jeupersonnages.troupes.Troupe;

public class JeuPersonnages {

	private static final Logger LOG = LogManager.getLogger(JeuPersonnages.class);

	public static void main(String[] args) throws CloneNotSupportedException {
		// =========== Test ECouleur :
		LOG.info("==========Test ECouleur");
		ECouleur coul;
		LOG.info(ECouleur.bleu.getIndex());
		try {
			coul = ECouleur.getColor(2);
			LOG.info(coul);
			coul = ECouleur.getColor(22);
			LOG.info(coul);
		} catch (ECouleurException e) {
			LOG.error(e.getMessage());
		} finally {
			LOG.info("le bloc Finally ...");
		}

		// =========== Test Adresse :
		LOG.info("==========Test Adresse");
		Adresse ad1 = new Adresse(12, "Village1");
		Adresse ad2 = new Adresse(12, "Village1");
		Adresse ad3 = (Adresse) ad1.clone();
		LOG.info(ad1);
		LOG.info(ad1 == ad2);
		LOG.info(ad1.equals(ad2));
		LOG.info(ad1 == ad3);
		LOG.info(ad1.equals(ad3));
		LOG.info("Hashcode : " + (ad1.hashCode() == ad3.hashCode()));
		ad3 = null;
		System.gc();

		// =========== Test Ouvrier :
		LOG.info("==========Test Ouvrier");
		Ouvrier ouv1 = new Ouvrier("Eddard Stark", ECouleur.bleu, 30.0f, 10, new Adresse(22, "Winterfell"));
		LOG.info(ouv1);
		Ouvrier ouv2 = new Ouvrier("Tyrion Lannister", ECouleur.rouge, 3.0f, 10, new Adresse(13, "Castral Roc"));
		Ouvrier ouv3 = (Ouvrier) ouv2.clone();
		LOG.info(ouv2.equals(ouv3));
		ouv3 = null;

		// =========== Test Guerrier :
		LOG.info("==========Test Guerrier");
		Guerrier guer1 = new Guerrier("La Montagne",ECouleur.rouge, 25f, 35);
		LOG.info(guer1);
		Guerrier guer2 = (Guerrier)guer1.clone();
		LOG.info(guer1.equals(guer2));
		
		// =========== Test Dragon :
		LOG.info("==========Test Dragon");
		Dragon drag1 = new Dragon(ECouleur.jaune, 58f, 235);
		Dragon drag2 = (Dragon) drag1.clone();
		LOG.info(drag1);
		LOG.info(drag1.equals(drag2));
		drag1.deplacer();

		// =========== Test attaque :
		guer1.deplacer();
		guer1.setCible(ouv1);
		try {
			guer1.attaquer();
		} catch (CompAttaqueException e1) {
			e1.printStackTrace();
		}
		Guerrier guer3 = new Guerrier("Bob L'eponge",ECouleur.jaune, 25f, 35);
		guer1.setCompAtt(new ALaHache());
		guer1.setCible(guer3);
		try {
			guer1.attaquer();
		} catch (CompAttaqueException e1) {
			e1.printStackTrace();
		}
		
		// =========== Test troupe :
		LOG.info("==========Test Troupe");
		Troupe troupe1 = new Troupe("Tr01", ouv1);
		troupe1.ajoutPerso(ouv2);
		troupe1.ajoutPerso(guer1);
		troupe1.ajoutPerso(drag1);
		
		LOG.info("nb Perso : " + troupe1.nbPerso());
		LOG.info(troupe1);
		LOG.info(troupe1.contientPerso(ouv1));
		troupe1.deplacement();
		
		troupe1.aLAttaque();
		
		try {
			troupe1.supprPerso(ouv2);
			ouv2 = null;
			troupe1.supprPerso(ouv1);
			ouv1 = null;
		} catch (TroupeVideException e) {
			LOG.info("troupe vide");
			troupe1 = null;
			System.gc();
		}
	}
}
