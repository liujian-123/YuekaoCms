package com.liujian.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liujian.cms.dao.SpecialMapper;
import com.liujian.cms.domain.Special;
import com.liujian.cms.service.SpecialService;

@Service
public class SpecialServiceImpl implements SpecialService {
	@Resource
	private SpecialMapper specialMapper;

	@Override
	public int insert(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.insert(special);
	}

	@Override
	public int update(Special special) {
		// TODO Auto-generated method stub
		return specialMapper.update(special);
	}

	@Override
	public List<Special> selects() {
		// TODO Auto-generated method stub
		return specialMapper.selects();
	}

	@Override
	public int insertSpecialArticle(Integer sid, Integer aid) {
		// TODO Auto-generated method stub
		return specialMapper.insertSpecialArticle(sid, aid);
	}

	@Override
	public Special select(Integer sid) {
		// TODO Auto-generated method stub
		return specialMapper.select(sid);
	}

	/* (non Javadoc) 
	 * @Title: getByid
	 * @Description: TODO
	 * @param sid
	 * @return 
	 * @see com.liujian.cms.service.SpecialService#getByid(java.lang.Integer) 
	 */
	@Override
	public Special getByid(Integer sid) {
		// TODO Auto-generated method stub
		return specialMapper.getByid(sid);
	}

}
