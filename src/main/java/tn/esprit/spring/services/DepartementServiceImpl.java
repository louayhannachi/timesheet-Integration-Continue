package tn.esprit.spring.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.repository.DepartementRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class DepartementServiceImpl implements IDepartementService {

	@Autowired
	DepartementRepository deptRepository;
	
	private static final Logger l = LogManager.getLogger(DepartementServiceImpl.class);

	public List<Departement> getAllDepartements() {
		l.info("In getAllDepartement method");
		return (List<Departement>) deptRepository.findAll();
	}

	@Override
	public Departement addOrUpdateDep(Departement d) {
		l.info("In addOrUpdateDep method");
		Departement dept = deptRepository.save(d);
		l.info("In addOrUpdateDep method (after added) : "+ dept);
		return dept;
	}
	
	
	@Override
	public Departement addDepartement(Departement dep) {
		l.info("In Adding departement : " + dep);
		Departement savedDep = null;
		try {
		savedDep = deptRepository.save(dep);
		l.info("Out of  Adding departement (after returning) "); 
		} catch (Exception e) {
		  l.error("Out of  addDepartement with errors. : "+ e); 
		}
		finally {
		  l.info("Out of  method addDepartement. "); 
		}
		return savedDep; 
	}
	
	@Override 
	public Departement updateDepartement(Departement dep) {
		return deptRepository.save(dep);
	}

	@Override
	public Departement retrieveDepartement(int id) {
		l.info("In retrieving departement id = " + id);
		Departement dep =  deptRepository.findById(id).get(); 
		l.info("Departement returned : " + dep);
		return dep; 
	}

	@Override
	public void deleteDepartement(int id) {
		l.info("In deleting departement id = " + id);
		deptRepository.deleteById(id);		
	}
	

}
