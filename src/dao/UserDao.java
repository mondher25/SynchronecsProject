package dao;

import java.util.List;

import javax.ejb.Local;

import entities.User;

@Local
public interface UserDao {
	
	public void createUser(User u);
	public User login(String mail,String password);
	public User getUserByMailId(String mailId);
	public List<User> listeUser(Long id);
}
