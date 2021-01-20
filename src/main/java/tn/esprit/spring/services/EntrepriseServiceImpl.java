package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EntrepriseRepository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EntrepriseServiceImpl implements IEntrepriseService {

	@Autowired
    EntrepriseRepository entrepriseRepoistory;
	@Autowired
	DepartementRepository deptRepoistory;
	
	private static final Logger l = LogManager.getLogger(EntrepriseServiceImpl.class);

	
	public int ajouterEntreprise(Entreprise entreprise) {
		entrepriseRepoistory.save(entreprise);
		return entreprise.getId();
	}

	public int ajouterDepartement(Departement dep) {
		deptRepoistory.save(dep);
		return dep.getId();
	}
	
	public void affecterDepartementAEntreprise(int depId, int entrepriseId) {
		//Le bout Master de cette relation N:1 est departement  
				//donc il faut rajouter l'entreprise a departement 
				// ==> c'est l'objet departement(le master) qui va mettre a jour l'association
				//Rappel : la classe qui contient mappedBy represente le bout Slave
				//Rappel : Dans une relation oneToMany le mappedBy doit etre du cote one.
				Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
				Departement depManagedEntity = deptRepoistory.findById(depId).get();
				
				depManagedEntity.setEntreprise(entrepriseManagedEntity);
				deptRepoistory.save(depManagedEntity);
		
	}
	
	public List<String> getAllDepartementsNamesByEntreprise(int entrepriseId) {
		Entreprise entrepriseManagedEntity = entrepriseRepoistory.findById(entrepriseId).get();
		List<String> depNames = new ArrayList<>();
		for(Departement dep : entrepriseManagedEntity.getDepartements()){
			depNames.add(dep.getName());
		}
		
		return depNames;
	}

	@Transactional
	public void deleteEntrepriseById(int entrepriseId) {
		entrepriseRepoistory.delete(entrepriseRepoistory.findById(entrepriseId).get());	
	}

	@Transactional
	public void deleteDepartementById(int depId) {
		deptRepoistory.delete(deptRepoistory.findById(depId).get());	
	}


	public Entreprise getEntrepriseById(int entrepriseId) {
		return entrepriseRepoistory.findById(entrepriseId).get();	
	}
	
	// home work 
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
		Entreprise e =  entrepriseRepoistory.findById(id).orElse(null);
		l.info("Entreprise returned : " + e);
		return e; 
	}
	
	

}
