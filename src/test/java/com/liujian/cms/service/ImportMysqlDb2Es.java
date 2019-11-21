/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ImportMysqlDb2Es.java 
 * @Prject: liujian-cms
 * @Package: com.liujian.cms.service 
 * @Description: TODO
 * @author: 刘建
 * @date: 2019年11月19日 下午2:54:47 
 * @version: V1.0   
 */
package com.liujian.cms.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.liujian.cms.dao.ArticleMapper;
import com.liujian.cms.dao.ArticleRepository;
import com.liujian.cms.domain.ArticleWithBLOBs;

/** 
 * @ClassName: ImportMysqlDb2Es 
 * @Description: TODO
 * @author:刘建
 * @date: 2019年11月19日 下午2:54:47  
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ImportMysqlDb2Es {

	@Autowired
	ArticleMapper articleMapper;
	@Autowired
	private ArticleRepository articleRepository;
	@Test
	public void testImport() {
		List<ArticleWithBLOBs> list = articleMapper.selects(null);
		System.out.println(list);
		articleRepository.saveAll(list);
	}
}
