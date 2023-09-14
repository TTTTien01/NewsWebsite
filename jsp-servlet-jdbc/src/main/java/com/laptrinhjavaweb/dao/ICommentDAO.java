package com.laptrinhjavaweb.dao;
import java.util.List;

import com.laptrinhjavaweb.model.CommentModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface ICommentDAO extends GenericDAO<CommentModel>{
	
	int getTotalItems();
	
	List<CommentModel> findAll(Pageable pageable);
	
	List<CommentModel> findByNewsId(Long newsId);
	
	CommentModel findOne(Long id);

	Long save(CommentModel comment);

	void update(CommentModel comment);
	
	void delete(long id);
	
}
