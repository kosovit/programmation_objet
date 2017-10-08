package fr.ensma.ia.jeupersonnages.troupes;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

import fr.ensma.ia.jeupersonnages.comportement.IDepEtAtt;
import fr.ensma.ia.jeupersonnages.outils.exceptions.TroupeVideException;
import mockit.Expectations;
import mockit.FullVerifications;
import mockit.Mocked;
import mockit.integration.junit4.JMockit;

@RunWith(JMockit.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestTroupe {

	@Mocked
	IDepEtAtt perso1, perso2;
	private Troupe laTroupe; 

	private static final Logger LOG = LogManager.getLogger(TestTroupe.class);
	
	@Before
	public void initTest() {
		laTroupe = new Troupe("tr", perso1);
	}

	@Test
	public void test02_nbPerso() {
		Assert.assertEquals(1, laTroupe.nbPerso());
	}

	@Test
	public void test03_ajoutPerso() {
		new Expectations() {
		};
		laTroupe.ajoutPerso(perso2);
		Assert.assertEquals(2, laTroupe.nbPerso());
		new FullVerifications() {
			{
				perso2.setMaTroupe(laTroupe);
			}
		};
	}

	@Test(expected = TroupeVideException.class)
	public void test04_supprPerso() throws TroupeVideException {
		laTroupe.ajoutPerso(perso2);
		laTroupe.supprPerso(perso1);
		Assert.assertEquals(1, laTroupe.nbPerso());
		laTroupe.supprPerso(perso1);
		Assert.assertEquals(1, laTroupe.nbPerso());
		laTroupe.supprPerso(perso2);
	}

	@Test
	public void test05_contientPerso() {
		Assert.assertTrue(laTroupe.contientPerso(perso1));
		Assert.assertFalse(laTroupe.contientPerso(perso2));
	}

	@Test
	public void test06_toString() {
		new Expectations() {
			{
				perso1.toString();result = "Perso";
			}
		};
		Assert.assertEquals("La troupe tr :\nPerso\n-----\n", laTroupe.toString());
		new FullVerifications() {
			{
				perso1.toString();
				times = 1;
			}
		};
	}

	@Test
	public void test07_deplacement() {
		new Expectations() {
		};
		laTroupe.deplacement();
		new FullVerifications() {
			{
				perso1.deplacer();
				
			}
		};
	}
	
	@Test
	public void test08_finalize(){
		laTroupe = null;
		System.gc();
	}
	
	@AfterClass
	public static void FinTest(){
		LOG.info("Fin des tests Troupe");
	}

}
