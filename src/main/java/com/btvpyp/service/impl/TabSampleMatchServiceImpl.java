package com.btvpyp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabSampleDao;
import com.btvpyp.dao.TabSampleMatchDao;
import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabSampleMatch;
import com.btvpyp.service.TabSampleMatchService;
/**
 * 样本匹配流水单服务层实现类
 * @author gaobo
 *
 */
@Service
public class TabSampleMatchServiceImpl implements TabSampleMatchService{
	@Autowired
	public TabSampleMatchDao tabSampleMatchDao;
	@Autowired
	public TabSampleDao tabSampleDao;
	@Override
	public List<TabSampleMatch> selectTabSampleMatchs(
			TabSampleMatch tabSampleMatch) {
		return tabSampleMatchDao.selectTabSampleMatchs(tabSampleMatch);
	}
	
	@Override
	public Integer getTotalCount(TabSampleMatch tabSampleMatch) {
		return tabSampleMatchDao.getTotalCount(tabSampleMatch);
	}

	@Override
	public Integer insertTabSampleMatch(TabSampleMatch tabSampleMatch) {
		int sampleMatchId = 0;
		//计算时长 --start
//		Integer startTime = Integer.parseInt(tabSampleMatch.getStartTime());
//		Integer endTime = Integer.parseInt(tabSampleMatch.getEndTime());
//		int length = (endTime - startTime);
//		tabSampleMatch.setLength(length);
		//计算时长--end
		tabSampleMatch.setDeleteFlg(0);
		sampleMatchId = tabSampleMatchDao.insertTabSampleMatch(tabSampleMatch);
		
		return sampleMatchId;
	}
	
	//由于北大匹配精度不够，临时处理办法(暂时不启用，作为预案)
	public TabSampleMatch change(TabSampleMatch tabSampleMatch, int sampleMatchId){
		int preId = sampleMatchId -1;//前一条记录的id
		String sampleId = tabSampleMatch.getSampleId();//该条流水对应的样本id
		TabSample tabSample = tabSampleDao.selectObjById(sampleId);
		int length = tabSample.getLength();//样本真实的时长
		Integer startTime = Integer.parseInt(tabSampleMatch.getStartTime());
		Integer endTime = Integer.parseInt(tabSampleMatch.getEndTime());
		if((length-(endTime-startTime)) == 1){
			endTime = endTime + 1;
		}
		return null;
	}

	@Override
	public Integer updateTabSampleMatch(TabSampleMatch tabSampleMatch) {
		return tabSampleMatchDao.updateTabSampleMatch(tabSampleMatch);
	}

	@Override
	public Integer deleteTabSampleMatch(TabSampleMatch tabSampleMatch) {
		return tabSampleMatchDao.deleteTabSampleMatch(tabSampleMatch);
	}

	@Override
	public TabSampleMatch getOneById(Integer sampleMatchId) {
		return tabSampleMatchDao.getOneById(sampleMatchId);
	}

}
