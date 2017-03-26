package dao;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserDao {
	
	public void createUser(User u);
	public User login(String mail,String password);
}
