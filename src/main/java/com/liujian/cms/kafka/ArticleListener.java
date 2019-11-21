/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: ArticleListener.java 
 * @Prject: liujian-cms
 * @Package: com.liujian.cms.kafka 
 * @Description: TODO
 * @author: 刘建
 * @date: 2019年11月13日 下午2:14:06 
 * @version: V1.0   
 */
package com.liujian.cms.kafka;

import java.sql.Date;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.listener.MessageListener;

import com.alibaba.fastjson.JSON;
import com.liujian.cms.dao.ArticleMapper;
import com.liujian.cms.domain.ArticleWithBLOBs;

/** 
 * @ClassName: ArticleListener 
 * @Description: TODO
 * @author:刘建
 * @date: 2019年11月13日 下午2:14:06  
 */
public class ArticleListener implements MessageListener<String , String>{

	@Autowired
	private ArticleMapper articleWithBLOBs;
	/* (non Javadoc) 
	 * @Title: onMessage
	 * @Description: TODO
	 * @param data 
	 * @see org.springframework.kafka.listener.GenericMessageListener#onMessage(java.lang.Object) 
	 */
	@Override
	public void onMessage(ConsumerRecord<String, String> msg) {
//		接受文章	
		String jsonString = msg.value();
		System.out.println(jsonString);
//		文章转jaon对象  用阿里fastjson
		ArticleWithBLOBs bs = JSON.parseObject(jsonString,ArticleWithBLOBs.class);
		articleWithBLOBs.insertSelective(bs);
		System.out.println("成功");
	}

}
