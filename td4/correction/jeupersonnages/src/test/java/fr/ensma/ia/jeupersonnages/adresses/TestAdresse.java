package fr.ensma.ia.jeupersonnages.adresses;

import java.io.UnsupportedEncodingException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

/**
 * Classe des tests unitaires de la classe Adresse.
 * @author michaelrichard
 *
 */

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAdresse {

	private Adresse adrRef;
	private static final Logger LOG = LogManager.getLogger(TestAdresse.class);
	
	@Before
	public void initTestAdresse(){
		adrRef = new Adresse(5, "Village");
	}
	
	@Test
	public void test01_getNomVillage(){
		Assert.assertTrue(adrRef.getNomVillage().compareTo("Village")==0);
	}
	
	@Test(expected=NullPointerException.class)
	public void test01_setNomVillage(){
		adrRef.setNomVillage(null);
	}
	
	@Test
	public void test02_toString() throws UnsupportedEncodingException{
		Assert.assertEquals(adrRef.toString(),("Hutte numéro " 
			+ adrRef.getNumHutte() + ", " + adrRef.getNomVillage()));
	}
	
	@Test
	public void test03_equals(){
		Assert.assertTrue(adrRef.equals(adrRef));
		Assert.assertFalse(adrRef.equals(null));
		Assert.assertFalse(adrRef.equals(new Integer(0)));
		Adresse ad = new Adresse(5, "Village");
		Assert.assertTrue(adrRef.equals(ad));
		adrRef.setNomVillage("");
		Assert.assertFalse(adrRef.equals(ad));
		adrRef.setNomVillage("Village");
		adrRef.setNumHutte(4);
		Assert.assertFalse(adrRef.equals(ad));
		adrRef.setNomVillage("");
		Assert.assertFalse(adrRef.equals(ad));
	}
	
	@Test(expected=NullPointerException.class)
	public void test04_hashCode(){
		Adresse ad = new Adresse(5, "Village");
		Assert.assertEquals(adrRef.hashCode(),ad.hashCode());
		ad.setNumHutte(0);
		Assert.assertNotEquals(adrRef.hashCode(),ad.hashCode());
		ad.setNumHutte(5);
		ad.setNomVillage("");
		Assert.assertNotEquals(adrRef.hashCode(),ad.hashCode());
		ad.setNomVillage(null);
	}
	
	@Test
	public void test05_clone() throws CloneNotSupportedException{
		Adresse ad = (Adresse)adrRef.clone();
		Assert.assertFalse(adrRef==ad);
		Assert.assertTrue(adrRef.equals(ad));
	}
	
	@Test(expected=NullPointerException.class)
	public void test06_finalize() throws CloneNotSupportedException{
		Adresse ad = (Adresse)adrRef.clone();
		ad = null;
		System.gc();
		LOG.info("Fin des tests ...");
		ad.setNomVillage("");
	}
}
