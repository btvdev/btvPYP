package com.btvpyp.detail.service;

import java.util.List;

import com.btvpyp.broadBack.model.TabBroadBack;
import com.btvpyp.detail.model.TabDetail;
import com.btvpyp.sampleMatch.model.TabSampleMatch;

public interface TabDetailService {
	public Integer insertTabDetail(TabBroadBack tabBroadBack);
	public Integer insertTabDetail(TabSampleMatch tabSampleMatch);
	public List<TabDetail> selectTabDetails(TabDetail tabDetail);
}
