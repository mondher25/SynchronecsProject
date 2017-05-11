package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Planner;

 

 

@Local
public interface PlannerDao {

	public void AddPlanner(Planner planner);
	public List<Planner> getAllPlanner();
	public Planner getPlannerById(Long id);
	public List<Planner> getAllPlannerByMailId(String mail);
	public List<Planner> getAllPlannerByMailAndnomSociete (String mail,String nomSociete);
	public List<Planner> getAllPlannerById(Long id);
	public void remove(Planner planner);
	public void updatePlanner(Planner planner);
	public List<String> listPlanner();
	
}
