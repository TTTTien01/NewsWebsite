package com.laptrinhjavaweb.service.impl;

import java.sql.Timestamp;
import java.util.List;

import javax.inject.Inject;

import com.laptrinhjavaweb.dao.ICommentDAO;
import com.laptrinhjavaweb.dao.IUserDAO;
import com.laptrinhjavaweb.model.CommentModel;
import com.laptrinhjavaweb.model.UserModel;
import com.laptrinhjavaweb.paging.Pageable;
import com.laptrinhjavaweb.service.ICommentService;

public class CommentService implements ICommentService {
	
	@Inject
	private ICommentDAO commentDAO;
	
	@Inject
	private IUserDAO userDAO;
	
	@Override
	public CommentModel findOne(Long id) {
		CommentModel comment = commentDAO.findOne(id);
		
		return comment;
	}

	@Override
	public List<CommentModel> findAll(Pageable pageable) {
		
		List<CommentModel> comments = commentDAO.findAll(pageable);
		
		// set userName
		for (CommentModel comment : comments) {
			UserModel user = userDAO.findOne(comment.getUserId());
			
			comment.setUserName(user.getUserName());
		}
		
		return comments;
	}

	@Override
	public CommentModel save(CommentModel model) {
		
		model.setCreatedDate(new Timestamp(System.currentTimeMillis()));
		
		Long id = commentDAO.save(model);
		
		return commentDAO.findOne(id);
	}

	@Override
	public List<CommentModel> findByNewsId(Long newsId) {
		
		List<CommentModel> comments = commentDAO.findByNewsId(newsId);
		
		// set userName
		for (CommentModel comment : comments) {
			UserModel user = userDAO.findOne(comment.getUserId());
			
			comment.setUserName(user.getUserName());
		}
		
		return comments;
	}

	@Override
	public int getTotalItems() {
		
		return commentDAO.getTotalItems();
	}

	@Override
	public CommentModel update(CommentModel updateComment) {
		CommentModel oldComment = commentDAO.findOne(updateComment.getId());

		updateComment.setCreatedDate(oldComment.getCreatedDate());
		updateComment.setCreatedBy(oldComment.getCreatedBy());
		updateComment.setModifiedDate(new Timestamp(System.currentTimeMillis()));
		
		commentDAO.update(updateComment);

		return commentDAO.findOne(updateComment.getId());
	}

	@Override
	public void delete(long[] ids) {
		for (long id: ids) {
			// 1. delete comment
			// ...
			// 2. delete news
			commentDAO.delete(id);
		}
	}

}
