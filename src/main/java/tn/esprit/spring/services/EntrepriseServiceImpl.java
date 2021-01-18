package tn.esprit.spring.services;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.EntrepriseRepository;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImplTest.class);
	
	@Override
	public List<Entreprise> retrieveAllEntreprise() {
		l.info("In  retrieveAllEntreprise : "); 
		List<Entreprise> entrepriseList = (List<Entreprise>) entrepriseRepoistory.findAll();  
		for (Entreprise e : entrepriseList) {
			l.debug("Entreprise +++ : " + e);
		}
		l.info("Out of retrieveAllEntreprises."); 
		return entrepriseList;
	}
	
	@Override
	public Entreprise addEntreprise(Entreprise e) {
		l.info("In  addEntreprise : " + e); 
		Entreprise entreprise = entrepriseRepoistory.save(e);
		l.info("Out of  addEntreprise. "); 
		return entreprise; 
	}

	@Override 
	public Entreprise updateEntreprise(Entreprise e) {
		l.info("In  updateEntreprise : " + e); 
		return entrepriseRepoistory.save(e);
	}

	@Override
	public void deleteEntreprise(int id) {
		l.info("In  deleteEntreprise : " + id); 
		entrepriseRepoistory.deleteById(id);
	}

	@Override
	public Entreprise retrieveEntreprise(int id) {
		l.info("in  retrieveEntreprise id = " + id);
		Entreprise e =  entrepriseRepoistory.findById(id).get();
		l.info("Entreprise returned : " + e);
		return e; 
	}
	
}
