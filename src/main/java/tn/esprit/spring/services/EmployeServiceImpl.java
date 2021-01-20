package tn.esprit.spring.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Departement;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Timesheet;
import tn.esprit.spring.repository.ContratRepository;
import tn.esprit.spring.repository.DepartementRepository;
import tn.esprit.spring.repository.EmployeRepository;
import tn.esprit.spring.repository.TimesheetRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Service
public class EmployeServiceImpl implements IEmployeService {
	@Autowired
	EmployeRepository employeRepository;
	@Autowired
	DepartementRepository deptRepoistory;
	@Autowired
	ContratRepository contratRepoistory;
	@Autowired
	TimesheetRepository timesheetRepository;
	
	private static final Logger l = LogManager.getLogger(EmployeServiceImpl.class);
	

	@Override
	public List<Employe> retrieveAllEmployes() {
		l.info("In  retrieveAllEmployes : "); 
		List<Employe> employes = (List<Employe>) employeRepository.findAll();  
		for (Employe employe : employes) {
			l.debug("employe +++ : " + employe);
		}
		l.info("Out of retrieveAllEmployes."); 
		return employes;
	}
	
	
	
	@Override
	public Employe addEmploye(Employe e) {
		l.info("In  addEmployer : " + e); 
		Employe employeSaved = employeRepository.save(e);
		l.info("Out of  addEmployer. "); 
		return employeSaved; 
	}
	
	
	@Override 
	public Employe updateEmploye(Employe e) {
		return employeRepository.save(e);
	}

	@Override
	public void deleteEmploye(int id) {
		employeRepository.deleteById(id);
	}

	@Override
	public Employe retrieveEmploye (int id) {
		l.info("in  retrieveEmploye id = " + id);
		// Optional retrun type - Java 8 (susceptible de retourner des valeurs «vides» et pas null)
	//	Employe e =  employeRepository.findById(Long.parseLong(id)).orElse(null);
		Employe e =  employeRepository.findById(id).get(); 
		l.info("Employe returned : " + e);
		return e; 
	}


	@Override
	public Employe authenticate(String login, String password) {
		return employeRepository.getEmployeByEmailAndPassword(login, password);
	}


	
	
	

	@Override
	public int addOrUpdateEmploye(Employe employe) {
		employeRepository.save(employe);
		return employe.getId();
	}


	public void mettreAjourEmailByEmployeId(String email, int employeId) {
		Employe employe = employeRepository.findById(employeId).get();
		employe.setEmail(email);
		employeRepository.save(employe);

	}

		
	public void affecterEmployeADepartement(int employeId, int depId) { 
		Departement d = deptRepoistory.findById(depId).get();
		Employe e = employeRepository.findById(employeId).get();
		// Employe et le fils (contient le mappedBy) donc : 
		d.getEmployes().add(e);
	}
	
	@Transactional
	public void desaffecterEmployeDuDepartement(int employeId, int depId)
	{
		Departement dep = deptRepoistory.findById(depId).get();
		Employe empl = employeRepository.findById(employeId).get();
		
		dep.getEmployes().remove(empl);
		

//		int employeNb = dep.getEmployes().size();
//		for(int index = 0; index < employeNb; index++){
//			if(dep.getEmployes().get(index).getId() == employeId){
//				dep.getEmployes().remove(index);
//				break;//a revoir
//			}
//		}
		
	} 
	
	// Tablesapce (espace disque) 

	public int ajouterContrat(Contrat contrat) {
		contratRepoistory.save(contrat);
		return contrat.getReference();
	}

	public void affecterContratAEmploye(int contratId, int employeId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		Employe employeManagedEntity = employeRepository.findById(employeId).get();

		contratManagedEntity.setEmploye(employeManagedEntity);

		// faux: 
		//employeManagedEntity.setContrat(contratManagedEntity);

	}

	public String getEmployePrenomById(int employeId) {
		Employe employeManagedEntity = employeRepository.findById(employeId).get();
		return employeManagedEntity.getPrenom();
	}
	 
	public void deleteEmployeById(int employeId)
	{
		Employe employe = employeRepository.findById(employeId).get();

		//Desaffecter l'employe de tous les departements
		//c'est le bout master qui permet de mettre a jour
		//la table d'association
		for(Departement dep : employe.getDepartements()){
			dep.getEmployes().remove(employe);
		}

		employeRepository.delete(employe);
	}

	public void deleteContratById(int contratId) {
		Contrat contratManagedEntity = contratRepoistory.findById(contratId).get();
		contratRepoistory.delete(contratManagedEntity);

	}

	public int getNombreEmployeJPQL() {
		return employeRepository.countemp();
	}

	public List<String> getAllEmployeNamesJPQL() {
		return employeRepository.employeNames();

	}

	public List<Employe> getAllEmployeByEntreprise(Entreprise entreprise) {
		return employeRepository.getAllEmployeByEntreprisec(entreprise);
	}

	public void mettreAjourEmailByEmployeIdJPQL(String email, int employeId) {
		employeRepository.mettreAjourEmailByEmployeIdJPQL(email, employeId);

	}
	public void deleteAllContratJPQL() {
		employeRepository.deleteAllContratJPQL();
	}

	public float getSalaireByEmployeIdJPQL(int employeId) {
		return employeRepository.getSalaireByEmployeIdJPQL(employeId);
	}

	public Double getSalaireMoyenByDepartementId(int departementId) {
		return employeRepository.getSalaireMoyenByDepartementId(departementId);
	}

	public List<Timesheet> getTimesheetsByMissionAndDate(Employe employe, Mission mission, Date dateDebut,
			Date dateFin) {
		return timesheetRepository.getTimesheetsByMissionAndDate(employe, mission, dateDebut, dateFin);
	}

	public List<Employe> getAllEmployes() {
		return (List<Employe>) employeRepository.findAll();
	}

	
}

























