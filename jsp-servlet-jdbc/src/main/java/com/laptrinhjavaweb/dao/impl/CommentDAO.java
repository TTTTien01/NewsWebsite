package com.laptrinhjavaweb.dao.impl;

import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.laptrinhjavaweb.dao.ICommentDAO;
import com.laptrinhjavaweb.mapper.CommentMapper;
import com.laptrinhjavaweb.model.CommentModel;
import com.laptrinhjavaweb.paging.Pageable;

public class CommentDAO extends AbstractDAO<CommentModel> implements ICommentDAO {

	@Override
	public CommentModel findOne(Long id) {

		String sql = "SELECT * FROM comment WHERE id = ?";

		List<CommentModel> comments = query(sql, new CommentMapper(), id);

		return comments.isEmpty() ? null : comments.get(0);
	}

	@Override
	public Long save(CommentModel comment) {
		StringBuilder sql = new StringBuilder("INSERT INTO comment (content, user_id, new_id,");
		sql.append(" invalid, createddate, createdby)");
		sql.append(" VALUES (?, ?, ?, ?, ?, ?)");

		return insert(sql.toString(), comment.getContent(), comment.getUserId(), comment.getNewsId(), 0,
				comment.getCreatedDate(), comment.getCreatedBy());
	}

	@Override
	public List<CommentModel> findByNewsId(Long newsId) {

		String sql = "SELECT * FROM comment where new_id=?";

		return query(sql, new CommentMapper(), newsId);
	}

	@Override
	public List<CommentModel> findAll(Pageable pageable) {
		StringBuilder sql = new StringBuilder("SELECT * FROM comment");

		if (pageable != null) {
			if (pageable.getSoter() != null && StringUtils.isNotBlank(pageable.getSoter().getSortName())
					&& StringUtils.isNotBlank(pageable.getSoter().getSortBy())) {
				sql.append(" ORDER BY " + pageable.getSoter().getSortName() + " " + pageable.getSoter().getSortBy());
			}

			if (pageable.getOffset() != null && pageable.getLimit() != null) {
				sql.append(" LIMIT " + pageable.getOffset() + ", " + pageable.getLimit());
			}
		}

		return query(sql.toString(), new CommentMapper());
	}

	@Override
	public int getTotalItems() {

		return count("SELECT count(*) FROM comment");
	}

	@Override
	public void update(CommentModel comment) {

		StringBuilder sql = new StringBuilder("UPDATE comment SET content = ?, user_id = ?, new_id = ?,");
		sql.append(" invalid = ?, createddate = ?, createdby = ?, modifieddate = ?, modifiedby = ?");
		sql.append(" WHERE id = ?");

		update(sql.toString(), comment.getContent(), comment.getUserId(), comment.getNewsId(), comment.getInvalid(),
				comment.getCreatedDate(), comment.getCreatedBy(), comment.getModifiedDate(), comment.getModifiedBy(),
				comment.getId());
	}

	@Override
	public void delete(long id) {

		String sql = "DELETE FROM comment WHERE id = ?";
		update(sql, id);
	}

}
