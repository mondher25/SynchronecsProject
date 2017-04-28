package dao;

import java.util.List;

import javax.ejb.Local;

import entities.Comment;

@Local
public interface CommentDao {

	public void addComment(Comment c);
	public List<Comment> listCommentByTacheId(Long idTache);
	public List<Comment> getAllComment();
}
