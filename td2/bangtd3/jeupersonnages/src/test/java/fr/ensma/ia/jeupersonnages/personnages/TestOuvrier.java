package fr.ensma.ia.jeupersonnages.personnages;


import java.util.Objects;

import org.junit.*;
import org.junit.runner.RunWith;

import mockit.*;
import mockit.integration.junit4.JMockit;
import fr.ensma.ia.jeupersonnages.adresses.Adresse;
import fr.ensma.ia.jeupersonnages.outils.ECouleur;

@RunWith(JMockit.class)
public class TestOuvrier {
	private static Ouvrier ouvrier1;
	
	@BeforeClass
	public static void initTest() {
		ouvrier1 = new Ouvrier(ECouleur.rouge, 122.12f, 12, null);
	}
	
	@Test
	public void testGetNiveauVie() {
		Assert.assertEquals(122.12f, ouvrier1.getNiveauVie(), 0f);
	}
	
	@Test
	public void testSetNiveauVie() {
		ouvrier1.setNiveauVie(200.0f);
		Assert.assertEquals(200.0f, ouvrier1.getNiveauVie(), 0f);
	}
	
	@Test
	public void testGetPuissance() {
		Assert.assertEquals(12, ouvrier1.getPuissance());
	}
	
	@Test
	public void testSetPuissance() {
		ouvrier1.setPuissance(20);
		Assert.assertEquals(20, ouvrier1.getPuissance());
	}
	
	@Mocked
	Adresse adresse;
	
	@Test
	public void testToString() {
		new Expectations() {
			{
				adresse.toString();
				returns("My Adresse");
			}
		};
		Ouvrier ouvrier2 = new Ouvrier("john", ECouleur.rouge, 200.00f, 20, adresse);
		
		Assert.assertEquals("john, rouge, 200.0, 20\nMy Adresse", ouvrier2.toString());
		new Verifications() {
			{
				adresse.toString(); 
				times = 1;
			}
		};
	}
	
	@Mocked
	Adresse adresse1;
	Adresse adresse2;
	
	@Test
	public void testEquals() {
		Assert.assertFalse(ouvrier1.equals(null));
		Assert.assertTrue(ouvrier1.equals(ouvrier1));
		Assert.assertFalse(ouvrier1.equals(new Integer(2)));		
	
		new Expectations() {
			{
				Objects.equals(adresse1, adresse2);
				returns(false);
				Objects.equals(adresse1, adresse1);
				result = true;
			}
		};
		
		Ouvrier ouvrier2 = new Ouvrier("john", ECouleur.rouge, 200.0f, 20, adresse1);
		Ouvrier ouvrier3 = new Ouvrier("john", ECouleur.rouge, 200.0f, 20, adresse2);
		Ouvrier ouvrier4 = new Ouvrier("john", ECouleur.rouge, 200.0f, 20, adresse1);
		Assert.assertTrue(ouvrier2.equals(ouvrier4));
		Assert.assertFalse(ouvrier2.equals(ouvrier3));
	
		new Verifications() {
			{
				Objects.equals(adresse1, adresse2);
				times = 1;
				Objects.equals(adresse1, adresse1);
				times = 1;
			}
		};
		
		Ouvrier ouvrier5 = new Ouvrier(ECouleur.rouge, 200.0f, 20, adresse1);
		Assert.assertFalse(ouvrier2.equals(ouvrier5));
		
		Ouvrier ouvrier6 = new Ouvrier("john", ECouleur.vert, 200.0f, 20, adresse1);
		Assert.assertFalse(ouvrier2.equals(ouvrier6));
		
		Ouvrier ouvrier7 = new Ouvrier("john", ECouleur.rouge, 100.0f, 20, adresse1);
		Assert.assertFalse(ouvrier2.equals(ouvrier7));
		
		Ouvrier ouvrier8 = new Ouvrier("john", ECouleur.rouge, 200.0f, 10, adresse1);
		Assert.assertFalse(ouvrier2.equals(ouvrier8));
	}
	
	@Mocked
	Adresse adresse3;
	
	@Test
	public void testClone() throws CloneNotSupportedException {
		new Expectations() {
			{
				adresse3.clone();
				result = adresse3;
			}
		};
		
		Ouvrier ouvrier2 = new Ouvrier("john", ECouleur.rouge, 200f, 20, null);
		Ouvrier ouvrier3 = new Ouvrier("john", ECouleur.rouge, 300f, 30, adresse3);
		
		Assert.assertTrue(ouvrier2.equals(ouvrier2.clone()));
		Assert.assertTrue(ouvrier3.equals(ouvrier3.clone()));
		
		new Verifications() {
			{
				adresse3.clone();
				times = 1;
			}
		};
	}
	
	@Mocked
	Adresse adresse4;
	
	@Test
	public void testHashCode() {
		new Expectations() {
			{
				adresse4.hashCode();
				result = 10;
			}
		};
		
		Ouvrier ouvrier2 = new Ouvrier("john", ECouleur.rouge, 200f, 20, null);
		Ouvrier ouvrier2Clone = new Ouvrier("john", ECouleur.rouge, 200f, 20, null);
		Ouvrier ouvrier3 = new Ouvrier("john", ECouleur.rouge, 300f, 30, adresse4);
		Ouvrier ouvrier3Clone = new Ouvrier("john", ECouleur.rouge, 300f, 30, adresse4);
		
		Assert.assertEquals(ouvrier2.hashCode(), ouvrier2Clone.hashCode());
		Assert.assertEquals(ouvrier3.hashCode(), ouvrier3Clone.hashCode());
		
		new Verifications() {
			{
				adresse4.hashCode();
				times = 2;
			}
		};
	}
	
	@Mocked
	Adresse adresse5;
	
	@Test
	public void testSetMonAdresse() {
		ouvrier1.setMonAdresse(adresse5);
		Assert.assertTrue(adresse5.equals(ouvrier1.getMonAdresse()));
	}
}
