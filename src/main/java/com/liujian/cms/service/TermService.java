package com.liujian.cms.service;

import com.liujian.cms.domain.Term;

public interface TermService {
	

	/**
	 * 
	 * @Title: selectByName 
	 * @Description: 根据标签查询标签对象
	 * @param name
	 * @return
	 * @return: Term
	 */
  Term selectByName(String name);
	
	
	/**
	 * 
	 * @Title: insert 
	 * @Description: 保存标签
	 * @param term
	 * @return
	 * @return: int
	 */
	int insert(Term term);
}
