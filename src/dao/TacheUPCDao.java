package dao;

import javax.ejb.Local;

import entities.TacheUPC;

@Local
public interface TacheUPCDao {

	
	public void addAffTache(TacheUPC tacheUPC);
}
