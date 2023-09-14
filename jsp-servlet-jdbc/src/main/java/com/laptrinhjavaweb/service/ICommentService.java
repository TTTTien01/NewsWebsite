package com.laptrinhjavaweb.service;

import java.util.List;

import com.laptrinhjavaweb.model.CommentModel;
import com.laptrinhjavaweb.paging.Pageable;

public interface ICommentService {
	
	int getTotalItems();
	
	CommentModel findOne(Long id);
	
	List<CommentModel> findByNewsId(Long newsId);
	
	List<CommentModel> findAll(Pageable pageable);
	
	CommentModel save(CommentModel model);
	
	CommentModel update(CommentModel model);
	
	void delete(long[] ids);
	
}
