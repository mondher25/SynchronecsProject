package dao;

import java.util.List;

import javax.ejb.Local;

import entities.TacheUPC;

@Local
public interface TacheUPCDao {

	
	public void addAffTache(TacheUPC tacheUPC);
	public List<TacheUPC> getTacheByPlannerCompartimentUser(Long idp,Long idCom,Long idTache,String mail);
	public List<TacheUPC> getTacheByPlannerCompartiment(Long idp,Long idCom);
	public List<TacheUPC> getTacheById(Long idTache);
	public TacheUPC findTacheUPCById(Long id);
	public void delete(Long id);
	public void deleteComp(Long id);
	public void deletePlannerAffTacheUPC(Long id);
	public List<TacheUPC> getTacheByUser(Long idp,Long idCom);
	
	 
}
