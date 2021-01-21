package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Departement;


public interface IDepartementService {
	 
	public List<Departement> getAllDepartements();
	public Departement addOrUpdateDep(Departement d); 
	
	public Departement addDepartement(Departement d);	 
	public Departement updateDepartement(Departement d);
	public Departement retrieveDepartement (int id);
	void deleteDepartement(int id);
 	
}
