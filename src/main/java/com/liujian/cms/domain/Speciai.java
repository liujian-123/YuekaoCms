/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: Speciai.java 
 * @Prject: liujian-cms
 * @Package: com.liujian.cms.domain 
 * @Description: TODO
 * @author: 刘建
 * @date: 2019年10月30日 上午8:47:03 
 * @version: V1.0   
 */
package com.liujian.cms.domain;

import java.io.Serializable;
import java.util.Date;

/** 
 * @ClassName: Speciai 
 * @Description: TODO
 * @author:刘建
 * @date: 2019年10月30日 上午8:47:03  
 */
public class Speciai implements Serializable{

	private Integer id;
	private String title;
	private String abstracts;//专题摘要
	private Date datetime;//创建时间
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAbstracts() {
		return abstracts;
	}
	public void setAbstracts(String abstracts) {
		this.abstracts = abstracts;
	}
	public Date getDatetime() {
		return datetime;
	}
	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abstracts == null) ? 0 : abstracts.hashCode());
		result = prime * result + ((datetime == null) ? 0 : datetime.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Speciai other = (Speciai) obj;
		if (abstracts == null) {
			if (other.abstracts != null)
				return false;
		} else if (!abstracts.equals(other.abstracts))
			return false;
		if (datetime == null) {
			if (other.datetime != null)
				return false;
		} else if (!datetime.equals(other.datetime))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}
