package dao;

import java.util.List;

import javax.ejb.Local;

import entities.SupPlanner;

@Local
public interface SupPlannerDao {

	public void addSuperviseur(SupPlanner sup);
	public void deleteSupPlanner(Long id);
	public List<SupPlanner> getSupPlanner(Long idUser);
}
