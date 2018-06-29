package com.btvpyp.sample.service.impl;

import java.io.File;
import java.util.List;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.advType.dao.TabAdvTypesDao;
import com.btvpyp.sample.dao.TabSampleDao;
import com.btvpyp.sample.model.FingerPush;
import com.btvpyp.sample.model.FingerPushBack;
import com.btvpyp.sample.model.TabSample;
import com.btvpyp.sample.service.TabSampleService;
import com.btvpyp.sampleMatch.dao.TabSampleMatchDao;
import com.btvpyp.sampleMatch.model.TabSampleMatch;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.FileUtils;
import com.btvpyp.utils.HttpClient;
import com.btvpyp.utils.KeyGen;
import com.btvpyp.utils.LibraryUtils;
import com.btvpyp.utils.LogUtil;
import com.btvpyp.utils.StrUtils;
import com.btvpyp.utils.TimeUtil;
@Service
public class TabSampleServiceImpl implements TabSampleService {
	@Autowired
	public TabSampleDao tabSampleDao;
	@Autowired
	public TabAdvTypesDao tabAdvTypesDao;
	@Autowired
	public TabSampleMatchDao tabSampleMatchDao;
	
	@Override
	public List<TabSample> selectTabSamples(TabSample tabSample) {
		return tabSampleDao.selectTabSamples(tabSample);
	}
	
	@Override
	public String insertTabSample(TabSample tabSample) {
		//如果没有频道，则默认BTV1
		String pid = tabSample.getPid();
		if(null == pid || "".equals(pid)) {
			tabSample.setPid("BTV1");
		}
		String columnType = tabSample.getColumnType();
		//如果样本类型为节目，则时长置为空
		if(columnType.equals("jm")) {
			tabSample.setLength(0);
		}
		String fileAddr = tabSample.getFileAddr();
		if(null != fileAddr && !"".equals(fileAddr)){
			if(!fileAddr.contains(".mp4") || fileAddr.contains(".MP4")) {
				tabSample.setFileAddr("");
				tabSample.setFileNetAddr("");
				tabSample.setExtFlag(4);  //如果fileAddr不包含mp4，则extFlag设置为4，代表暂时不推送指纹
			}
		}
		/*自动计算出最新的品牌号*/
		Integer brandBef = tabSample.getBrandId();
		if(null == brandBef || brandBef == 0) {
			Integer brandId = generateNewBrandId();
			tabSample.setBrandId(brandId + 1);
		}
		
		Integer re = tabSampleDao.insertTabSample(tabSample);
//		re = 0;//接口联调前暂时不要注释这一行，彻底走接口后该行需要注释掉！
		if(re == 1){ 
			//生成TabSamplePush对象,调用对方接口
			if(tabSample.getExtFlag() == 0 || tabSample.getExtFlag() == 2) { //如果样本没有mp4文件，则不进行推送当虹
				push(tabSample);
			}
			synchTLS_add(tabSample);
		}
		return tabSample.getSampleId();
	}
	
	public String synchTLS_add(TabSample tabSample) {
		String soapStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
			   "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
			   "<soap:Body><addProgMsg xmlns=\"http://tempuri.org/\">";
		soapStr += "<gg_idno>" + tabSample.getBrandId().toString() + "</gg_idno>";
		soapStr += "<gg_name>" + tabSample.getMainName() + "</gg_name>";
		soapStr += "<gg_subname>" + tabSample.getSecondName() + "</gg_subname>";
		String firstDate = tabSample.getFirstDate();
		if(StrUtils.isNotEmptyStr(firstDate)) {
			soapStr += "<gg_date>" + TimeUtil.formatDate(tabSample.getFirstDate()) + "</gg_date>";
		}else {
			soapStr += "<gg_date>" + "" + "</gg_date>";
		}
		String columnType = tabSample.getColumnType();
		if(StrUtils.isNotEmptyStr(columnType)){
			if(columnType.equals("adv") || columnType.equals("xc")) {
				columnType = "0";
			}
			if(columnType.equals("jm")) {
				columnType = "1";
			}
		}
		soapStr += "<gg_type>" + columnType + "</gg_type>";
		soapStr += "<gg_def>" + LibraryUtils.channelMap(tabSample.getPid()) +"</gg_def>";
		String length = tabSample.getLength().toString();
		if(!StrUtils.isNotEmptyStr(length)) {
			soapStr += "<gg_len>" + "0" + "</gg_len>";
		}else{
			soapStr += "<gg_len>" + length + "</gg_len>";
		}
		String firstTime = tabSample.getFirstTime();
		if(StrUtils.isNotEmptyStr(firstTime)) {
			soapStr += "<gg_script>" + firstTime + "</gg_script>";
		}else {
			soapStr += "<gg_script>" + "" + "</gg_script>";
		}
		String comments = tabSample.getComments();
		if(StrUtils.isNotEmptyStr(comments)) {
			soapStr += "<gg_froname>" + comments + "</gg_froname>";
		}else {
			soapStr += "<gg_froname>" + "" + "</gg_froname>";
		}
		String tapeNum = tabSample.getTapeNum();
		if(StrUtils.isNotEmptyStr(tapeNum)) {
			soapStr += "<gg_tapeno>" + tapeNum + "</gg_tapeno>";
		}else {
			soapStr += "<gg_tapeno>" + "" + "</gg_tapeno>";
		}
		String createrName = tabSample.getCreaterName();
		if(StrUtils.isNotEmptyStr(createrName)) {
			soapStr += "<add_person>" + createrName + "</add_person>";
		}else {
			soapStr += "<add_person>" + "" + "</add_person>";
		}
		soapStr += "</addProgMsg></soap:Body></soap:Envelope>";
		HttpClient.sendWsPost(Commons.REQUESTURL_ADD, soapStr);
		return "";
	}
	
	/**
	 * 样本重推
	 */
	@Override
	public void rePush(String sampleId) {
		TabSample tabSample = tabSampleDao.selectObjById(sampleId);
		push(tabSample);
	}
	
	/**
	 * 删除样本
	 */
	@Override
	public Integer deleteTabSample(String sampleId) {
		int result = 0;
		TabSample tabSample = tabSampleDao.selectObjById(sampleId);
		result = tabSampleDao.deleteTabSample(sampleId);
		tabSampleMatchDao.deleteBySampleId(sampleId);
//		result = 0;
		if(result != 0) {
			String url = Commons.FINGERDELADDR;
			String param = "sampleId=" + sampleId;
			String responseStr = HttpClient.sendPost(url, param); //返回值是true或false
			System.out.println(responseStr);
			
			synchTLS_del(tabSample);
		}
		return result;
	}
	
	public String synchTLS_del(TabSample tabSample) {
		String soapStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
				   "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
				   "<soap:Body><delProgMsg xmlns=\"http://tempuri.org/\">";
		soapStr += "<gg_idno>" + tabSample.getBrandId().toString() + "</gg_idno>";
		soapStr += "<gg_name>" + tabSample.getMainName() + "</gg_name>";
		soapStr += "<gg_subname>" + tabSample.getSecondName() + "</gg_subname>";
		String firstDate = tabSample.getFirstDate();
		if(StrUtils.isNotEmptyStr(firstDate)) {
			soapStr += "<gg_date>" + TimeUtil.formatDate(tabSample.getFirstDate()) + "</gg_date>";
		}else {
			soapStr += "<gg_date>" + "" + "</gg_date>";
		}
		String columnType = tabSample.getColumnType();
		if(StrUtils.isNotEmptyStr(columnType)){
			if(columnType.equals("adv") || columnType.equals("xc")) {
				columnType = "0";
			}
			if(columnType.equals("jm")) {
				columnType = "1";
			}
		}
		soapStr += "<gg_type>" + columnType + "</gg_type>";
		soapStr += "<gg_def>" + LibraryUtils.channelMap(tabSample.getPid()) +"</gg_def>";
		soapStr += "<gg_len>" + tabSample.getLength().toString() + "</gg_len>";
		String firstTime = tabSample.getFirstTime();
		if(StrUtils.isNotEmptyStr(firstTime)) {
			soapStr += "<gg_script>" + firstTime + "</gg_script>";
		}else {
			soapStr += "<gg_script>" + "" + "</gg_script>";
		}
		String comments = tabSample.getComments();
		if(StrUtils.isNotEmptyStr(comments)) {
			soapStr += "<gg_froname>" + comments + "</gg_froname>";
		}else {
			soapStr += "<gg_froname>" + "" + "</gg_froname>";
		}
		String tapeNum = tabSample.getTapeNum();
		if(StrUtils.isNotEmptyStr(tapeNum)) {
			soapStr += "<gg_tapeno>" + tapeNum + "</gg_tapeno>";
		}else {
			soapStr += "<gg_tapeno>" + "" + "</gg_tapeno>";
		}
		String createrName = tabSample.getCreaterName();
		if(StrUtils.isNotEmptyStr(createrName)) {
			soapStr += "<add_person>" + createrName + "</add_person>";
		}else {
			soapStr += "<add_person>" + "" + "</add_person>";
		}
		soapStr += "</delProgMsg></soap:Body></soap:Envelope>";
		HttpClient.sendWsPost(Commons.REQUESTURL_DELETE, soapStr); 
		return "";
	}
	
	/**
	 * 推送到当虹指纹注册接口
	 * @param tabSample
	 * @return
	 */
	public String push(TabSample tabSample){
		String responseStr = "";
		String url = Commons.FINGERPUSHADDR;
		//生成json字符串
		FingerPush fingerPush = new FingerPush();
		fingerPush.setSampleId(tabSample.getSampleId());
		fingerPush.setName(tabSample.getMainName());
		fingerPush.setProgramName(tabSample.getMainName());
		fingerPush.setType("Ad");
		fingerPush.setRecognizeAgain("true");
		String winFileAddr = tabSample.getFileAddr();
		String linFileAddr = winFileAddr.replace(Commons.VIDEOSAVE, Commons.DHSAVE);
		fingerPush.setSource(linFileAddr);
		String jsonStr = JSONObject.fromObject(fingerPush).toString();
		
		//调用对方指纹注册接口
		responseStr = HttpClient.doPost(url, jsonStr);
//		System.out.println("当虹返回值：----------" + responseStr);
		//将目标系统返回值转化成json
		JSONObject json =  JSONObject.fromObject(responseStr);
		FingerPushBack FingerPushBack = (FingerPushBack)JSONObject.toBean(json, FingerPushBack.class);
		String status = FingerPushBack.getStatus();
		if(status.contains("Success")) { //如果推送成功
			//写入日志文件
			String fileName = tabSample.getSampleId() + "_" + TimeUtil.getTimeYMdHMS() + ".txt";
			String saveUrl = Commons.PUSHDATADIR + TimeUtil.getTimeDir() + "/";
			jsonStr += responseStr;
			LogUtil.saveSampleJson(jsonStr, saveUrl, fileName);
			//更新extFlag的值
			tabSample.setExtFlag(3);
			tabSampleDao.updateTabSample(tabSample);
		}else {
			tabSample.setExtFlag(2);
			tabSampleDao.updateTabSample(tabSample);
		}
		return responseStr;
	}
	
	public Integer generateNewBrandId() {
		return tabSampleDao.selectMaxBrandId();
	}
	
	@Override
	public Integer updateTabSample(TabSample tabSample) {
		int result = 0;
		TabSample tabSample_bef = tabSampleDao.selectObjById(tabSample.getSampleId());
		result = tabSampleDao.updateTabSample(tabSample);
		synchTLS_update(tabSample, tabSample_bef);
		if(result == 1){
			if(tabSample.getExtFlag() == 4){
				String fileAddr = tabSample.getFileAddr();
				if(StrUtils.isNotEmptyStr(fileAddr)) {
					if(fileAddr.contains(".MP4") || fileAddr.contains(".mp4")){
						tabSample.setExtFlag(0);
						push(tabSample);
					}
				}
			}
		}
		TabSampleMatch tabSampleMatch = new TabSampleMatch();
		tabSampleMatch.setSampleId(tabSample.getSampleId());
		tabSampleMatch.setMainName(tabSample.getMainName());
		tabSampleMatch.setSecondName(tabSample.getSecondName());
		tabSampleMatch.setBrandId(tabSample.getBrandId());
		tabSampleMatch.setColumnType(tabSample.getColumnType());
		tabSampleMatchDao.updateMainName(tabSampleMatch);
		return result;
	}
	
	public String synchTLS_update(TabSample tabSample, TabSample tabSample_bef) {
		String soapStr = "<?xml version=\"1.0\" encoding=\"utf-8\"?>"+
				   "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"+
				   "<soap:Body><updProgMsg xmlns=\"http://tempuri.org/\">";
		soapStr += "<gg_idno>" + tabSample.getBrandId() + "</gg_idno>";
		soapStr += "<gg_name>" + tabSample.getMainName() + "</gg_name>";
		soapStr += "<gg_subname>" + tabSample.getSecondName() + "</gg_subname>";
		String firstDate = tabSample.getFirstDate();
		if(StrUtils.isNotEmptyStr(firstDate)) {
			soapStr += "<gg_date>" + TimeUtil.formatDate(tabSample.getFirstDate()) + "</gg_date>";
		}else {
			soapStr += "<gg_date>" + "" + "</gg_date>";
		}
		String columnType = tabSample.getColumnType();
		if(StrUtils.isNotEmptyStr(columnType)){
			if(columnType.equals("adv") || columnType.equals("xc")) {
				columnType = "0";
			}
			if(columnType.equals("jm")) {
				columnType = "1";
			}
		}
		soapStr += "<gg_type>" + columnType + "</gg_type>";
		soapStr += "<gg_def>" + LibraryUtils.channelMap(tabSample.getPid()) +"</gg_def>";
		String length = tabSample.getLength().toString();
		if(!StrUtils.isNotEmptyStr(length)) {
			soapStr += "<gg_len>" + "0" + "</gg_len>";
		}else{
			soapStr += "<gg_len>" + length + "</gg_len>";
		}
		
		String firstTime = tabSample.getFirstTime();
		if(StrUtils.isNotEmptyStr(firstTime)) {
			soapStr += "<gg_script>" + firstTime + "</gg_script>";
		}else {
			soapStr += "<gg_script>" + "" + "</gg_script>";
		}
		String comments = tabSample.getComments();
		if(StrUtils.isNotEmptyStr(comments)) {
			soapStr += "<gg_froname>" + comments + "</gg_froname>";
		}else {
			soapStr += "<gg_froname>" + "" + "</gg_froname>";
		}
		String tapeNum = tabSample.getTapeNum();
		if(StrUtils.isNotEmptyStr(tapeNum)) {
			soapStr += "<gg_tapeno>" + tapeNum + "</gg_tapeno>";
		}else {
			soapStr += "<gg_tapeno>" + "" + "</gg_tapeno>";
		}
		String createrName = tabSample.getCreaterName();
		if(StrUtils.isNotEmptyStr(createrName)) {
			soapStr += "<add_person>" + createrName + "</add_person>";
		}else {
			soapStr += "<add_person>" + "" + "</add_person>";
		}
		
		soapStr += "<gg_name_bef>" + tabSample_bef.getMainName() + "</gg_name_bef>";
		soapStr += "<gg_subname_bef>" + tabSample_bef.getSecondName() + "</gg_subname_bef>";
		String firstDate_bef = tabSample_bef.getFirstDate();
		if(StrUtils.isNotEmptyStr(firstDate_bef)) {
			soapStr += "<gg_date_bef>" + TimeUtil.formatDate(firstDate_bef) + "</gg_date_bef>";
		}else {
			soapStr += "<gg_date>" + "" + "</gg_date>";
		}
		String columnType_bef = tabSample_bef.getColumnType();
		if(StrUtils.isNotEmptyStr(columnType_bef)){
			if(columnType_bef.equals("adv") || columnType_bef.equals("xc")) {
				columnType_bef = "0";
			}
			if(columnType_bef.equals("jm")) {
				columnType_bef = "1";
			}
		}
		soapStr += "<gg_type_bef>" + columnType_bef + "</gg_type_bef>";
		soapStr += "<gg_def_bef>" + LibraryUtils.channelMap(tabSample_bef.getPid()) +"</gg_def_bef>";
		soapStr += "<gg_len_bef>" + tabSample_bef.getLength().toString() + "</gg_len_bef>";
		String firstTime_bef = tabSample_bef.getFirstTime();
		if(StrUtils.isNotEmptyStr(firstTime_bef)) {
			soapStr += "<gg_script_bef>" + firstTime_bef + "</gg_script_bef>";
		}else {
			soapStr += "<gg_script_bef>" + "" + "</gg_script_bef>";
		}
		String comments_bef = tabSample_bef.getComments();
		if(StrUtils.isNotEmptyStr(comments_bef)) {
			soapStr += "<gg_froname_bef>" + comments_bef + "</gg_froname_bef>";
		}else {
			soapStr += "<gg_froname_bef>" + "" + "</gg_froname_bef>";
		}
		String tapeNum_bef = tabSample_bef.getTapeNum();
		if(StrUtils.isNotEmptyStr(tapeNum_bef)) {
			soapStr += "<gg_tapeno_bef>" + tapeNum_bef + "</gg_tapeno_bef>";
		}else {
			soapStr += "<gg_tapeno_bef>" + "" + "</gg_tapeno_bef>";
		}
		String createrName_bef = tabSample_bef.getCreaterName();
		if(StrUtils.isNotEmptyStr(createrName_bef)) {
			soapStr += "<add_person_bef>" + createrName_bef + "</add_person_bef>";
		}else {
			soapStr += "<add_person_bef>" + "" + "</add_person_bef>";
		}
		soapStr += "</updProgMsg></soap:Body></soap:Envelope>";
		HttpClient.sendWsPost(Commons.REQUESTURL_UPDATE, soapStr);
		return "";
	}
	
	
	/////////////////////////////////////////////////////////////////////////////
	//从Excel导入数据的插入记录方法(暂时弃用)
		//先修改一下文件名，然后再进行推送
		@Override
		public String insertTabSampleFromExcel(TabSample tabSample) {
			String sampleId = KeyGen.getADVId();
			tabSample.setSampleId(sampleId);
			String fileLoc = tabSample.getFileAddr();
			File file = new File(fileLoc);
			String fileMainName = tabSample.getMainName();
			fileMainName = fileMainName.replaceAll("$", "");//去掉特殊符号
			if(file.exists()){ //如果文件存在，则进行文件名更改
				String newFileName = tabSample.getSampleId() + "_" + tabSample.getMainName() + ".mp4";
				String newPath = FileUtils.modifyFileName(tabSample.getFileAddr(), newFileName);
				String newNetAddr = tabSample.getFileNetAddr() + newFileName;
				tabSample.setFileAddr(newPath);
				tabSample.setFileNetAddr(newNetAddr);
//				System.out.println(newPath);
			}
			Integer re = tabSampleDao.insertTabSample(tabSample);
			
			//数据从Excel导入后暂时不向识别接口直接推送
			re = 0;
			if(re == 1){ 
				//生成TabSamplePush对象,调用对方接口
				push(tabSample);
			}
			return sampleId;
		}
		///////////////////////////////////////////////////////////////////////

	@Override
	public Integer getTotalCount(TabSample tabSample) {
		return tabSampleDao.getTotalCount(tabSample);
	}

	@Override
	public List<TabSample> findByIds(List<String> ids) {
		return tabSampleDao.findByIds(ids);
	}

	@Override
	public TabSample selectObjById(String sampleId) {
		return tabSampleDao.selectObjById(sampleId);
	}

	@Override
	public List<TabSample> selectUnpushTabSamples(TabSample tabSample) {
		return tabSampleDao.selectUnpushTabSamples(tabSample);
	}

	@Override
	public Integer getUnpushTotalCount(TabSample tabSample) {
		return tabSampleDao.getUnpushTotalCount(tabSample);
	}

	@Override
	public List<TabSample> selectByDimName(String mainName) {
		return tabSampleDao.selectByDimName(mainName);
	}

	@Override
	public String selectOneNameById(String sampleId) {
		return tabSampleDao.selectOneNameById(sampleId);
	}
}
