package jpa;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import dao.CommentDao;
import entities.Comment;

@Stateless
public class CommentJPA implements CommentDao{

	@PersistenceContext(unitName = "UP")
	EntityManager em;

	@Override
	public void addComment(Comment c) {
		em.persist(c);
		em.flush();
		
	}

	@Override
	public List<Comment> listCommentByTacheId(Long idTache) {
		List<Comment> listeComment=new ArrayList<>();
		listeComment=em.createQuery("SELECT c FROM Comment c WHERE tache_id=:id").setParameter("id", idTache).getResultList();
		
		return listeComment;
	}

	@Override
	public List<Comment> getAllComment() {
		List<Comment> listeComment=new ArrayList<>();
		listeComment=em.createQuery("SELECT c FROM Comment c ").getResultList();
		
		return listeComment;
	}

	@Override
	public void deleteTacheComm(Long idTache) {
		if(idTache != null)
			em.createNativeQuery("DELETE FROM Comment WHERE tache_id=:id").setParameter("id", idTache).executeUpdate();
		
	}
	
	
	
	
}
