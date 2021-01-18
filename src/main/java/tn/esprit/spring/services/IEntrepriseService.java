package tn.esprit.spring.services;

import java.util.List;

import tn.esprit.spring.entities.Entreprise;

public interface IEntrepriseService {
	public List<Entreprise> retrieveAllEntreprise();
	public Entreprise addEntreprise(Entreprise e);
	Entreprise updateEntreprise(Entreprise u);
	void deleteEntreprise(int id);
	Entreprise retrieveEntreprise(int id);
}
