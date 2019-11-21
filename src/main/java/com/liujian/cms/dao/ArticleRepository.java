/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleRepository.java 
 * @Prject: liujian-cms
 * @Package: com.liujian.cms.dao 
 * @Description: TODO
 * @author: 刘建
 * @date: 2019年11月19日 下午2:29:34 
 * @version: V1.0   
 */
package com.liujian.cms.dao;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.liujian.cms.domain.ArticleWithBLOBs;

/** 
 * @ClassName: ArticleRepository 
 * @Description: TODO
 * @author:刘建
 * @date: 2019年11月19日 下午2:29:34  
 */
//继承玩之后具备了CRUD的操作
public interface ArticleRepository extends ElasticsearchRepository<ArticleWithBLOBs,Integer > {

	//实现复杂查询
	//按照标题查询
	List<ArticleWithBLOBs> findByTitle(String key);
}
