package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.UserDao;
import entities.User;

/**
 * Session Bean implementation class Utilisateur
 */
@Stateless
public class UserJPA implements UserDao {

	@PersistenceContext(unitName="UP")
	EntityManager entityManager;
	
 

@Override
public User login(String mail, String password) {
	User user =null ;
	try{
		user = (User) entityManager.createQuery("SELECT u FROM User u WHERE mail=:mail AND password=:password").
				setParameter("mail", mail).
				setParameter("password", password).
				getSingleResult();
	}catch (Exception e) {
		return user;
	}

 
	return user;
}

@Override
public void createUser(User u) {
	entityManager.persist(u);
	
}

@Override
public List<User> listeUser(Long id) {
	List<User> listeUser=new ArrayList<>();
	listeUser=entityManager.createQuery("SELECT u FROM User u WHERE compte_id=:compte_id").setParameter("compte_id", id).getResultList();
	return listeUser;
}

@Override
public User getUserById(Long id) {
	 User user=new User();
	 user=entityManager.find(User.class, id);
	return user;
}


}
