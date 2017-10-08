package fr.ensma.ia.jeupersonnages.adresses;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.junit.Assert;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)//code will execute the test methods in the order of their names, sorted in ascending order
public class TestAdresse {
	private static Adresse adresse;
	
	@BeforeClass
	public static void initTest() {
		adresse = new Adresse(10, "poitiers");
	}
	
	@Test(expected=NullPointerException.class)
	public void testAdresseNullPointerException() throws NullPointerException { // need the throw??
		Adresse invalidAdresse = new Adresse(20, null);
	// TODO complete this
	}
	
	@Test
	public void testGetNomVillage() {
		Assert.assertEquals("poitiers", adresse.getNomVillage());
	}
	
	@Test
	public void testToString() {
//		"Hutte numero " + numHutte + ", " + nomVillage
//		"Hutte numero 10, poitiers"
		Assert.assertEquals("Hutte numero "+adresse.getNumHutte()+", "+adresse.getNomVillage(), adresse.toString());
	}
	
	/**
	 * Test strategy for equals
	 * obj: this, null, different class, same class, different attribute
	 */
	
	@Test
	public void testEqualsWithObjNull() {
		Assert.assertFalse(adresse.equals(null));
	}
	
	@Test
	public void testEqualsWithObjThis() {
		Assert.assertTrue(adresse.equals(adresse));
		
	}
	
	@Test
	public void testEqualsWithObjNotInstanceof() {
		Integer a = new Integer(10);
		Assert.assertFalse(adresse.equals(a));
	}
	
	@Test
	public void testEqualsWithObjInstanceof() {
		Adresse secondAdresse = new Adresse(20, "toulouse");
		Adresse secondAdresse1 = new Adresse(10, "toulouse");
		Adresse secondAdresse3 = new Adresse(30, "poitiers");
		Adresse sameAdresse = new Adresse(10, "poitiers");
		Assert.assertFalse(adresse.equals(secondAdresse));
		Assert.assertFalse(adresse.equals(secondAdresse3));
		Assert.assertFalse(adresse.equals(secondAdresse1));
		Assert.assertTrue(adresse.equals(sameAdresse));
	}
	
	/**
	 * Test strategy for hashCode();
	 * 2 equal objects, same hashcode
	 * different objects, different hashcode
	 */
	@Test
	public void testHashCode() {
		Adresse secondAdresse = new Adresse(20, "toulouse");
		Adresse sameAdresse = new Adresse(10, "poitiers");
		Assert.assertEquals(adresse.hashCode(), sameAdresse.hashCode());
		Assert.assertNotEquals(adresse.hashCode(), secondAdresse.hashCode());
	}
	
	@Test
	public void testGetNumHutte() {
		Assert.assertEquals(10, adresse.getNumHutte());
	}
	
	@Test
	public void testSetNumHutte() {
		Adresse secondAdresse = new Adresse(20, "toulouse");
		secondAdresse.setNumHutte(30);
		Assert.assertEquals(30, secondAdresse.getNumHutte());
	}
	
	@Test
	public void testSetNomVillage() {
		Adresse secondAdresse = new Adresse(20, "toulouse");
		secondAdresse.setNomVillage("ensma");
		Assert.assertEquals("ensma", secondAdresse.getNomVillage());
	}
	
	@Test
	public void testClone() throws CloneNotSupportedException {
		Adresse cloneAdresse = (Adresse) adresse.clone();
		Assert.assertTrue(cloneAdresse.equals(adresse));// clone to get  equals true ? ok 
	}
}
