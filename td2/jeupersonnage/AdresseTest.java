package jeupersonnages.adresses;

import junit.framework.Assert ;

import junit.framework.TestCase;
import junit.framework.TestSuite;
 
import org.junit.Test;

import fr.ensma.ia.jeupersonnages.adresses.Adresse;

public class AdresseTest {
	
	@Test
	public void testgetNomVillage() throws CloneNotSupportedException{
		Adresse ad = new Adresse (20, "Bd. des freres lumieres");
		Assert.assertEquals("Bd. des freres lumieres", ad.getNomVillage());
		ad.toString();
		Adresse ad2;
		ad2 = (Adresse) ad.clone();
		//System.out.println(ad == ad2);
		Assert.assertEquals(ad, ad2);

		
		
		
		
	}
	
	

}
