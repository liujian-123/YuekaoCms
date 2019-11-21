/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: SpeciaiServiceTest.java 
 * @Prject: liujian-cms
 * @Package: com.liujian.cms.service 
 * @Description: TODO
 * @author: 刘建
 * @date: 2019年10月30日 上午9:10:12 
 * @version: V1.0   
 */
package com.liujian.cms.service;

import static org.junit.Assert.*;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.web.bind.annotation.RequestMapping;

/** 
 * @ClassName: SpeciaiServiceTest 
 * @Description: TODO
 * @author:刘建
 * @date: 2019年10月30日 上午9:10:12  
 */
public class SpeciaiServiceTest {
	
	@Resource
	private SpecialService service;

	@Test
	public void test() {
		List selectss = service.selects();
		System.out.println(selectss);
	}

}
