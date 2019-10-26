package com.liujian.cms.dao;

import java.util.List;

import com.liujian.cms.domain.Channel;
/**
 * 
 * @ClassName: ChannelMapper 
 * @Description: 栏目
 * @author: liujian
 * @date: 2019年10月18日 上午10:24:45
 */
public interface ChannelMapper {
	/**
	 * 
	 * @Title: selects 
	 * @Description: TODO
	 * @return
	 * @return: List<Channel>
	 */
	List<Channel> selects();
	
	
    int deleteByPrimaryKey(Integer id);

    int insert(Channel record);

    int insertSelective(Channel record);

    Channel selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Channel record);

    int updateByPrimaryKey(Channel record);
}