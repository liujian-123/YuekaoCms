/**   
 * Copyright © 2019 公司名. All rights reserved.
 * 
 * @Title: SpeciaiArticle.java 
 * @Prject: liujian-cms
 * @Package: com.liujian.cms.domain 
 * @Description: TODO
 * @author: 刘建
 * @date: 2019年10月30日 上午8:54:42 
 * @version: V1.0   
 */
package com.liujian.cms.domain;

import java.io.Serializable;

/** 
 * @ClassName: SpeciaiArticle 
 * @Description: TODO
 * @author:刘建
 * @date: 2019年10月30日 上午8:54:42  
 */
public class SpeciaiArticle implements Serializable{

	private Integer sid;
	private Integer aid;
	public Integer getSid() {
		return sid;
	}
	public void setSid(Integer sid) {
		this.sid = sid;
	}
	public Integer getAid() {
		return aid;
	}
	public void setAid(Integer aid) {
		this.aid = aid;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((aid == null) ? 0 : aid.hashCode());
		result = prime * result + ((sid == null) ? 0 : sid.hashCode());
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
		SpeciaiArticle other = (SpeciaiArticle) obj;
		if (aid == null) {
			if (other.aid != null)
				return false;
		} else if (!aid.equals(other.aid))
			return false;
		if (sid == null) {
			if (other.sid != null)
				return false;
		} else if (!sid.equals(other.sid))
			return false;
		return true;
	}
	
	
}
