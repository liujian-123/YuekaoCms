package com.liujian.cms.service;

import com.liujian.cms.domain.User;
import com.liujian.cms.vo.UserVO;
import com.github.pagehelper.PageInfo;

public interface UserService {
	
	
	
	/**
	 * 
	 * @Title: login 
	 * @Description: 登陆
	 * @param user
	 * @return
	 * @return: User
	 */
	User login(User user);
	

	/**
	 * 
	 * @Title: selects 
	 * @Description: 列表查询
	 * @param username
	 * @return
	 * @return: List<User>
	 */
	PageInfo<User> selects(String username,Integer page,Integer pageSize);

     
    int insertSelective(UserVO userVO);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

}
