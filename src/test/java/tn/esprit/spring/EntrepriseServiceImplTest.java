package tn.esprit.spring;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.services.IEntrepriseService;

@SpringBootTest
public class EntrepriseServiceImplTest {
	
	@Autowired IEntrepriseService es;
	
	@Test
	public void testRetrieveAllEntreprise() {
		List<Entreprise> listEntreprise = es.retrieveAllEntreprise(); 
		Assertions.assertFalse(listEntreprise.isEmpty());
	}
	
	@Test
	public void testAddEntreprise() throws ParseException {
		Entreprise e = new Entreprise("ooredoo", "raison 1");
		Entreprise entrepriseAdded = es.addEntreprise(e); 
		Assertions.assertEquals(e.getName(), entrepriseAdded.getName());
	}
	
	@Test
	public void testUpdateEntreprise() throws ParseException   {
		Entreprise e = new Entreprise("orange", "raison 2");
		Entreprise entrepriseUpdated = es.updateEntreprise(e); 
		Assertions.assertEquals(e.getName(), entrepriseUpdated.getName());
	}
	
	@Test
	public void testDeleteEntreprise() {	
		//List<Entreprise> listEntreprise = es.retrieveAllEntreprise(); 
	    //Integer id = listEntreprise.get(0).getId();
	   // es.deleteEntreprise(id);
	    //Assertions.assertNull(es.retrieveEntreprise(id));
	}
}

