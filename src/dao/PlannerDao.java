package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Planner;

 

 

@Local
public interface PlannerDao {

	public void AddPlanner(String name,String type);
	public List<Planner> getAllPlanner();
	public Planner getPlannerById(Long id);
}
