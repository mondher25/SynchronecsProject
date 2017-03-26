package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Tache;

@Local
public interface TacheDao {

	public void addTache(Tache t);
	public List<Tache> getTacheByCompartiment(Long id);
}
