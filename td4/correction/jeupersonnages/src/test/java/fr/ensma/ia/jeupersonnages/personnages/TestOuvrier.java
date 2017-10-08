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

import fr.ensma.ia.jeupersonnages.adresses.Adresse;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;
import fr.ensma.ia.jeupersonnages.personnages.Guerrier;
import fr.ensma.ia.jeupersonnages.personnages.Ouvrier;
import fr.ensma.ia.jeupersonnages.personnages.comportement.deplacement.IDeplacement;
import fr.ensma.ia.jeupersonnages.troupes.Troupe;
import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.StrictExpectations;
import mockit.Verifications;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestOuvrier {
	
	private Ouvrier ouv;
	
	@Mocked Adresse ad,ad2;

	private static final Logger LOG = LogManager.getLogger(TestOuvrier.class);
	
	@Before
	public void initTestOuvrier(){
		ouv = new Ouvrier("Bob", ECouleur.jaune, 25.0f, 5, ad);
	}

	@Test
	public void test01_Ouvrier(){
		Ouvrier o = new Ouvrier(ECouleur.bleu, 1.0f, 5, null);
		Assert.assertEquals("Ouvrier2",o.getNom());
		Assert.assertEquals(1.0f,o.getNiveauVie(),.0);
		Assert.assertEquals(5,o.getPuissance());
		Assert.assertEquals(ECouleur.bleu,o.getCouleur());
	}

	@Test
	public void test02_toString() throws CloneNotSupportedException{
		new Expectations() {{
			ad.toString(); result = "Adresse de remplacement";
		}};
		
		Assert.assertEquals("Bob, jaune, 25.0, 5\nAdresse de remplacement", ouv.toString());
		
		new Verifications(){{
			ad.toString();times = 1;	
		}};
	}
	
	@Test
	public void test03_equals(){
		Assert.assertTrue(ouv.equals(ouv));
		Assert.assertFalse(ouv.equals(null));
		Assert.assertFalse(ouv.equals(new Integer(0)));
		
		Ouvrier ouv2 = new Ouvrier("Bob", ECouleur.jaune, 25.0f, 5, ad2);
		new Expectations() {{
			ad.equals(any); returns(true,false);			
		}};
		Assert.assertTrue(ouv.equals(ouv2));
		Assert.assertFalse(ouv.equals(ouv2));
		new Verifications() {{
			Adresse ad3;
			ad.equals(ad3 = withCapture()); times = 2;
			Assert.assertTrue(ad3==ouv2.getMonAdresse());
		}};
		ouv2.setCouleur(ECouleur.vert);
		Assert.assertFalse(ouv.equals(ouv2));
		ouv2.setCouleur(ECouleur.jaune);
		
		
		ouv2.setNiveauVie(0.0f);
		Assert.assertFalse(ouv.equals(ouv2));
		ouv2.setNiveauVie(25.0f);
		ouv2.setPuissance(25);
		Assert.assertFalse(ouv.equals(ouv2));
	}
	
	@Test
	public void test04_hashCode(){
		Ouvrier ouv2 = new Ouvrier("Bob", ECouleur.jaune, 25.0f, 5, null);
		ouv.setMonAdresse(null);
		Assert.assertTrue(ouv.hashCode()==ouv2.hashCode());
		
		ouv.setMonAdresse(ad);
		ouv2.setMonAdresse(ad);
		new Expectations() {{
			ad.hashCode(); result = 0;
		}};
		Assert.assertTrue(ouv.hashCode()==ouv2.hashCode());
		new Verifications() {{
			ad.hashCode(); times = 2;
		}};
	}
	
	@Test
	public void test05_clone() throws CloneNotSupportedException{
		ouv.setMonAdresse(ad);
		new Expectations() {{
			ad.clone(); times = 1; result = null;
		}};
		Ouvrier o = (Ouvrier)ouv.clone();
		o.setMonAdresse(ad);
		Assert.assertEquals(ouv,o);
		ouv.setMonAdresse(null);
		o = (Ouvrier)ouv.clone();
		Assert.assertEquals(ouv,o);	
	}
	
	@Test
	public void test07_deplacer(@Mocked final Personnage p, @Mocked IDeplacement dep){	
		new StrictExpectations() {{
				p.setCompDeplacement(dep);
		        p.deplacer();
		        ouv.compDep.deplacer();     
		 }};
		ouv.setCompDeplacement(dep); 
		ouv.deplacer();
	}
	
	@Test
	public void test07_tuVasMourrir(@Mocked Guerrier guer){
		float nv = ouv.getNiveauVie();
		ouv.tuVasMourrir(10,guer);
		Assert.assertEquals(nv-1.0f, ouv.getNiveauVie(),0);
	}
	
	@Test
	public void test08_setMaTroupe(@Mocked Troupe tr){
		ouv.setMaTroupe(tr);
		Assert.assertEquals(tr,ouv.getMaTroupe());
		new FullVerifications() {};
	}
	
	@Test(expected=NullPointerException.class)
	public void test09_finalize(){
		ouv = null;
		System.gc();
		ouv.setPuissance(12);
	}
	
	@AfterClass
	public static void FinTest(){
		LOG.info("Fin des tests Ouvrier");
	}
}
