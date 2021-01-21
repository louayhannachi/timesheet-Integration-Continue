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
		return (List<Departement>) deptRepository.findAll();
	}

	@Override
	public Departement addOrUpdateDep(Departement d) {
		Departement dept = deptRepository.save(d);
		return dept;
	}
	
	
	@Override
	public Departement addDepartement(Departement dep) {
		l.info("In Adding departement : " + dep); 
		Departement savedDep = deptRepository.save(dep);
		l.info("Out of  Adding departement. "); 
		return savedDep; 
	}
	
	@Override 
	public Departement updateDepartement(Departement dep) {
		return deptRepository.save(dep);
	}

	@Override
	public Departement retrieveDepartement(int id) {
		l.info("in  retrieving departement id = " + id);
		Departement dep =  deptRepository.findById(id).get(); 
		l.info("departement returned : " + dep);
		return dep; 
	}

	@Override
	public void deleteDepartement(int id) {
		deptRepository.deleteById(id);		
	}
	

}
