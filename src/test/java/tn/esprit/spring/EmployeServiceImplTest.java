package tn.esprit.spring;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Role;
import tn.esprit.spring.services.IEmployeService;


@SpringBootTest
public class EmployeServiceImplTest {
	
	@Autowired 
	IEmployeService es; 

	@Test
	public void testAddEmploye() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-23");
		//(int id, String prenom, String nom, String email, String password, boolean actif, Role role) 
		Employe e = new Employe(1,"abdelaali","chaima", "chaima.abdelaali@esprit.tn", "123", false, Role.CHEF_DEPARTEMENT); 
		Employe EmployeAdded = es.addEmploye(e); 
		Assertions.assertEquals(e.getPrenom(), EmployeAdded.getPrenom());
	}
	
	@Test
	public void testModifyEmploye() throws ParseException   {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date d = dateFormat.parse("2015-03-23");
		Employe e = new Employe(1,"abdelaali","chaima", "chaima.abdelaali@esprit.tn", "123", false, Role.CHEF_DEPARTEMENT); 
		Employe EmployeUpdated  = es.updateEmploye(e); 
		Assertions.assertEquals(e.getPrenom(), EmployeUpdated.getPrenom());
	}

	@Test
	public void testRetrieveAllEmployes() {
		List<Employe> listEmployes = es.retrieveAllEmployes(); 
		Assertions.assertFalse(listEmployes.isEmpty());
	}

	@Test
	public void testRetrieveEmploye() {
		Employe EmployeRetrieved = es.retrieveEmploye(1); 
		Assertions.assertEquals(1L, EmployeRetrieved.getId());
	}

}
