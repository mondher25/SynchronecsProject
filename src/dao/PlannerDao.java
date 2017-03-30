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
}
