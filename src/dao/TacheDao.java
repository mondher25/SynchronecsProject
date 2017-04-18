package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Tache;

@Local
public interface TacheDao {

	public void addTache(Tache t);
	public void updateTache(Tache t);
	public List<Tache> getTacheByCompartiment(Long id);
	public List<Tache> getTacheByCompartimentAndUser(Long idCom,String mail);
	public void removeTache(Long id);
	public List<Tache> getAllTache(Long idUser);
	public Tache findTacheById(Long id);
	public void remove(Tache tache);
 }
