package jpa;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import dao.UserDao;
import entities.User;

/**
 * Session Bean implementation class Utilisateur
 */
@Stateless
public class UtilisateurJPA implements UserDao {

	@PersistenceContext(unitName="UP")
	EntityManager entityManager;
	
public void createUser(String mail,String password){
	User u=new User();
	u.setMail(mail);
	u.setPassword(password);
	entityManager.persist(u);

}

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


}
