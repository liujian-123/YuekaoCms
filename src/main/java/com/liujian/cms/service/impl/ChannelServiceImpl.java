package com.liujian.cms.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.liujian.cms.dao.ChannelMapper;
import com.liujian.cms.domain.Channel;
import com.liujian.cms.service.ChannelService;

@Service
public class ChannelServiceImpl implements ChannelService {

	@Resource
	private ChannelMapper channelMapper;
	@Override
	public List<Channel> selects() {
		return channelMapper.selects();
	}

}
