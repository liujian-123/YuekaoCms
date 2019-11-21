package com.liujian.cms.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liujian.cms.dao.TermMapper;
import com.liujian.cms.domain.Term;
import com.liujian.cms.service.TermService;

@Service
public class TermServiceImpl implements TermService {
	@Resource
	private TermMapper termMapper;

	@Override
	public int insert(Term term) {
		// TODO Auto-generated method stub
		return termMapper.insert(term);
	}

	@Override
	public Term selectByName(String name) {
		// TODO Auto-generated method stub
		return termMapper.selectByName(name);
	}

}
