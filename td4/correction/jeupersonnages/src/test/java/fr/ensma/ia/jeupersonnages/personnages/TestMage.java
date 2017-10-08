package fr.ensma.ia.jeupersonnages.personnages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.personnages.comportement.deplacement.IDeplacement;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.StrictExpectations;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestMage {

	private Mage mge;
	
	private static final Logger LOG = LogManager.getLogger(TestMage.class);
	
	@Before
	public void initTest(){
		mge = new Mage("Varice", ECouleur.jaune, 12.0f, 15, 0);
	}
	
	@Test
	public void test01_GetSetNivSort(){
		Assert.assertEquals(0,mge.getNiveauSort());
		mge.setNiveauSort(2);
		Assert.assertEquals(2,mge.getNiveauSort());
		Mage mge2 = new Mage(ECouleur.bleu, 10f, 5, 0);
		Assert.assertEquals("Mage2", mge2.getNom());
	}
	
	@Test
	public void test01v1_GetSetPuissanceAtt(){
		Assert.assertEquals(15, mge.getPuissanceAtt());
		mge.setPuissanceAtt(20);
		Assert.assertEquals(20, mge.getPuissanceAtt());
	}
	
	@Test
	public void test02_toString(){
		Assert.assertEquals("Varice, jaune, 12.0, 15, 0", mge.toString());
	}
	
	@Test
	public void test03_equals(){
		Assert.assertTrue(mge.equals(mge));
		Assert.assertFalse(mge.equals(null));
		Assert.assertFalse(mge.equals(new Integer(0)));
		
		Mage mge2 = new Mage("Varice", ECouleur.jaune, 12.0f, 15, 0);
		Assert.assertTrue(mge.equals(mge2));
		mge2.setCouleur(ECouleur.bleu);
		Assert.assertFalse(mge.equals(mge2));
		mge2.setCouleur(ECouleur.jaune);
		mge2.setPuissanceAtt(0);
		Assert.assertFalse(mge.equals(mge2));
		mge2.setPuissanceAtt(15);
		mge2.setNiveauSort(2);
		Assert.assertFalse(mge.equals(mge2));
		mge2.setPuissanceAtt(0);
		Assert.assertFalse(mge.equals(mge2));
	}
	
	@Test
	public void test03v1_clone() throws CloneNotSupportedException{
		Assert.assertTrue(mge.equals(mge.clone()));
	}
	
	@Test
	public void test03v2_hashCode() throws CloneNotSupportedException{
		Mage mge2 = (Mage)mge.clone();
		Assert.assertEquals(mge.hashCode(), mge2.hashCode());
		mge2.setNiveauSort(5);
		Assert.assertNotEquals(mge.hashCode(), mge2.hashCode());
		mge2.setNiveauSort(0);
		mge2.setPuissanceAtt(0);
		Assert.assertNotEquals(mge.hashCode(), mge2.hashCode());
	}
	
	
	@Test
	public void test04_deplacer(@Mocked final Personnage p, @Mocked IDeplacement dep) {
		mge.setCompDeplacement(dep);
		mge.deplacer();
		new FullVerifications() {
			{
				p.setCompDeplacement(dep);
				p.deplacer();
				mge.compDep.deplacer();
			}
		};
	}

	@Test
	public void test05_setCible(@Mocked PersonnageHumain pers){
		mge.setCible(pers);
		new FullVerifications() {{
		}};
	}
	
	@Test
	public void test05v1_attaquer(@Mocked final PersonnageHumain pers2){
		Logger log2 = LogManager.getLogger(Mage.class);
		new StrictExpectations(log2) {
			{
				pers2.getNom();
				log2.info(anyString);
				pers2.tuVasMourrir(mge.getPuissanceAtt(), mge);
			}
		};
		mge.setCible(pers2);
		mge.attaquer();
	}
	
	@Test
	public void test05v2_tuVasMourrir(@Mocked final Personnage p,@Mocked final PersonnageHumain ph,@Mocked Guerrier att){
		Logger log2 = LogManager.getLogger(Mage.class);
		new StrictExpectations(log2) {
			{
				ph.setMonAttaquant(att);
				p.getNiveauVie() ; result = 5f;
				p.setNiveauVie(5f-10/10);
				p.getNiveauVie() ;
				log2.info(anyString);
				ph.getNom();
				ph.getMonAttaquant();
				ph.getNom();
				log2.info(anyString);
			}
		};
		mge.tuVasMourrir(10, att);
	}
	
	@AfterClass
	public static void finTest(){
		LOG.info("Fin des tests TestMage");
	}
}
