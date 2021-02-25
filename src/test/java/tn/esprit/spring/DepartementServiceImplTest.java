package tn.esprit.spring;

import org.springframework.boot.test.context.SpringBootTest;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.services.IDepartementService;

import java.text.ParseException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;



@SpringBootTest
public class DepartementServiceImplTest {
	
	@Autowired IDepartementService depService;
	
	
	@Test
	public void testgetAllDepartements() {
		List<Departement> list = depService.getAllDepartements(); 
		Assertions.assertFalse(list.isEmpty());
	}
	
	@Test
	public void testAddOrUpdateDep() throws ParseException {
		Departement d = new Departement("Departement Info");
		Departement addedDep = depService.addOrUpdateDep(d); 
		Assertions.assertEquals(d.getName(), addedDep.getName());
	}
	
	
	@Test
	public void testDeleteDepartement() {	
		List<Departement> list = depService.getAllDepartements(); 
	    Integer id = list.get(0).getId();
	    depService.deleteDepartement(id);
	    //Assertions.assertNull(depService.retrieveDepartement(id));
	}
	

}
