package com.btvpyp.detail.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.broadBack.model.TabBroadBack;
import com.btvpyp.detail.dao.TabDetailDao;
import com.btvpyp.detail.model.TabDetail;
import com.btvpyp.detail.service.TabDetailService;
import com.btvpyp.sampleMatch.model.TabSampleMatch;
import com.btvpyp.utils.TimeUtil;
@Service
public class TabDetailServiceImpl implements TabDetailService {
	
	@Autowired
	public TabDetailDao tabDetailDao;
	

	@Override
	public List<TabDetail> selectTabDetails(TabDetail tabDetail) {
		return tabDetailDao.selectTabDetails(tabDetail);
	}

	@Override
	public Integer insertTabDetail(TabBroadBack tabBroadBack) {
		int result = 0;
		if(null != tabBroadBack) {
			if(!tabBroadBack.getJmName().contains("广宣时段")) {
				TabDetail tabDetail = new TabDetail();
				tabDetail.setDataType(2);
				tabDetail.setProgramName(tabBroadBack.getJmName());
				Long startTime = TimeUtil.stringToTimestamp(tabBroadBack.getStarttime(), "yyyy-MM-dd HH:mm:ss");
				tabDetail.setStartTime(startTime.toString());
				Long endTime = TimeUtil.stringToTimestamp(tabBroadBack.getEndtime(), "yyyy-MM-dd HH:mm:ss");
				tabDetail.setEndTime(endTime.toString());
				tabDetail.setLength(tabBroadBack.getLength());
				tabDetail.setChannel(tabBroadBack.getChannel());
				result = tabDetailDao.insertTabDetail(tabDetail);
			}
		}
		return result;
	}

	@Override
	public Integer insertTabDetail(TabSampleMatch tabSampleMatch) {
		int result = 0;
		if(null != tabSampleMatch) {
			TabDetail tabDetail = new TabDetail();
			tabDetail.setDataType(1);
			tabDetail.setProgramName(tabSampleMatch.getMainName());
			tabDetail.setStartTime(tabSampleMatch.getStartTime());
			tabDetail.setEndTime(tabSampleMatch.getEndTime());
			tabDetail.setLength(tabSampleMatch.getLength());
			tabDetail.setChannel(tabSampleMatch.getPid());
			tabDetail.setComments(tabSampleMatch.getSecondName());
			tabDetail.setSampleId(tabSampleMatch.getSampleId());
			result = tabDetailDao.insertTabDetail(tabDetail);
		}
		return result;
	}
	
}
