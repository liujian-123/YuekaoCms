package com.liujian.cms.service;

import java.util.List;

import com.liujian.cms.domain.Category;

public interface CategoryService {

	/**
	 * 
	 * @Title: selectsByChannelId 
	 * @Description: 查询某个栏目下的分类
	 * @param channelId
	 * @return
	 * @return: List<Category>
	 */
	List<Category> selectsByChannelId(Integer channelId);
}
