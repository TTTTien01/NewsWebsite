package com.laptrinhjavaweb.paging;

import com.laptrinhjavaweb.sort.Sorter;

public interface Pageable {
	//lấy tranng hiện tại đang lấy
	Integer getPage();
	Integer getOffset();
	Integer getLimit();
	Sorter getSoter();
}
