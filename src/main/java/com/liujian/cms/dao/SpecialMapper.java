package com.liujian.cms.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.liujian.cms.domain.Article;
import com.liujian.cms.domain.ArticleWithBLOBs;
import com.liujian.cms.domain.Special;

public interface SpecialMapper {
	/**
	 * 
	 * @Title: insert 
	 * @Description: 增加专题
	 * @param special
	 * @return
	 * @return: int
	 */
	int insert(Special special); 
	/**
	 * 
	 * @Title: update 
	 * @Description: 修改专题
	 * @param special
	 * @return
	 * @return: int
	 */
	int update(Special special);
	/**
	 * 
	 * @Title: selects 
	 * @Description: 专题列表
	 * @return
	 * @return: List<Special>
	 */
	List<Special> selects();
	/**
	 * 
	 * @Title: insertSpecialArticle 
	 * @Description: 向专题增加文章
	 * @param sid
	 * @param aid
	 * @return
	 * @return: int
	 */
	int insertSpecialArticle(@Param("sid")Integer sid ,@Param("aid") Integer aid);
	/**
	 * 根据主键查询专题
	 * @Title: select 
	 * @Description: TODO
	 * @param sid
	 * @return
	 * @return: Special
	 */
	Special select(Integer sid);
	/** 
	 * @Title: getByid 
	 * @Description: TODO
	 * @param sid
	 * @return
	 * @return: Special
	 */
	Special getByid(Integer sid);
	
}
