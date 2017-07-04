package com.btvpyp.service.impl;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.net.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.btvpyp.dao.TabAdvTypesDao;
import com.btvpyp.dao.TabSampleDao;
import com.btvpyp.model.PushDataXmlBean;
import com.btvpyp.model.ReturnBean;
import com.btvpyp.model.TabAdvTypes;
import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabSamplePush;
import com.btvpyp.service.TabSampleService;
import com.btvpyp.utils.Commons;
import com.btvpyp.utils.FileUtils;
import com.btvpyp.utils.KeyGen;
import com.btvpyp.utils.LogUtil;
import com.btvpyp.utils.TimeUtil;
import com.btvpyp.utils.XmlUtils;
@Service
public class TabSampleServiceImpl implements TabSampleService {
	@Autowired
	public TabSampleDao tabSampleDao;
	@Autowired
	public TabAdvTypesDao tabAdvTypesDao;
	
	
	
	@Override
	public List<TabSample> selectTabSamples(TabSample tabSample) {
		return tabSampleDao.selectTabSamples(tabSample);
	}
	
	@Override
	public String insertTabSample(TabSample tabSample) {
		String sampleId = KeyGen.getADVId();
		tabSample.setSampleId(sampleId);
		Integer re = tabSampleDao.insertTabSample(tabSample);
		
//		re = 0;//接口联调前暂时不要注释这一行，彻底走接口后该行需要注释掉！
		if(re == 1){ 
			//生成TabSamplePush对象,调用北大推送接口
			push(tabSample);
		}
		return sampleId;
	}
	
	//从Excel导入数据的插入记录方法
	//先修改一下文件名，然后再进行推送
	@Override
	public String insertTabSample2(TabSample tabSample) {
		String sampleId = KeyGen.getADVId();
		tabSample.setSampleId(sampleId);
		
		String fileLoc = tabSample.getFileAddr();
		File file = new File(fileLoc);
		if(file.exists()){ //如果文件存在，则进行文件名更改
			String newFileName = tabSample.getSampleId() + ".mp4";
			String newPath = FileUtils.modifyFileName(tabSample.getFileAddr(), newFileName);
			String newNetAddr = tabSample.getFileNetAddr() + newFileName;
//			System.out.println(newPath);
		}
		
		Integer re = tabSampleDao.insertTabSample(tabSample);
		if(re == 1){ 
			//生成TabSamplePush对象,调用北大推送接口
			push(tabSample);
		}
		return sampleId;
	}
	
	public void saveJs(String dir){
		TabSample t = new TabSample();
		List<TabSample> tlist = tabSampleDao.selectTabSamples(t);
		String content = "";
		content += "[";
		if(null != tlist && tlist.size()>0){
			for(int i=0;i<tlist.size();i++){
				content += "\""+tlist.get(i).getLength()+"#"
						+tlist.get(i).getMainName()+"#"
						+tlist.get(i).getSecondName()+"\"";
				if(i<tlist.size()){
					content += ",";
				}
			}
		}
		content += "]";
		LogUtil.saveSampleJson(content, dir, "samples");
	}

	@Override
	public void rePush(String sampleId) {
		TabSample tabSample = tabSampleDao.selectObjById(sampleId);
		push(tabSample);
	}
	
	public int push(TabSample tabSample){
		int pushResult = 0;
		
		//sampleName 和 uri需要base64编码
		TabSamplePush tsp = new TabSamplePush();
		tsp.setSampleid(tabSample.getSampleId());
		String sname = tabSample.getMainName();
		byte[] bytes1 = sname.getBytes();
		tsp.setSamplename(new String(Base64.encodeBase64(bytes1)));
		tsp.setLength(tabSample.getLength());
		String fileAddr = tabSample.getFileAddr();
		byte[] bytes2 = fileAddr.getBytes();
		tsp.setUri(new String(Base64.encodeBase64(bytes2)));
		TabAdvTypes tat = tabAdvTypesDao.selectObjById(Integer.parseInt(tabSample.getMiddleType()));
		if(null != tat){
			tsp.setCategory(tat.getTypeId());
		}
		String rt = pushSampleData(tsp, tabSample.getSampleId());
//		System.out.println("北大返回值：--------------------------"+rt);
		ReturnBean rb = (ReturnBean)JSONObject.toBean(JSONObject.fromObject(rt), ReturnBean.class);
		if(rb.getCode().equals(Commons.PUSHCODESUCCESS)){
			//提取成功
			tabSample.setExtFlag(3);
			pushResult = 3;
		}else if(rb.getCode().equals(Commons.PUSHCODEFAIL)){
			//提取失败
			tabSample.setExtFlag(2);
		}else{
			//未提取
			tabSample.setExtFlag(0);
		}
		tabSampleDao.updateTabSample(tabSample);
		return pushResult;
	}
	
	public String pushSampleData(TabSamplePush tsp, String sampleId){
		String rt = "";
		
		try {
			URL postUrl = new URL(Commons.PUSHADDR);
			HttpURLConnection connection = (HttpURLConnection) postUrl.openConnection();
			// 设置是否向connection输出，因为这个是post请求，参数要放在
	        // http正文内，因此需要设为true
	        connection.setDoOutput(true);
	        connection.setDoInput(true);
	        connection.setRequestMethod("POST");
	        // Post 请求不能使用缓存
	        connection.setUseCaches(false);
	        connection.setInstanceFollowRedirects(true);
	        // 进行编码
	        connection.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
	        connection.connect();
	        DataOutputStream out = new DataOutputStream(connection.getOutputStream());
	        
	        String content = "";
	        content += "sampleid=" + tsp.getSampleid() + "&";
	        content += "samplename=" + tsp.getSamplename() + "&";
	        content += "uri=" + tsp.getUri() + "&";
	        content += "category=" + tsp.getCategory() + "&";
	        content += "length=" + tsp.getLength();
	        
//	        System.out.println("推送内容：------------------"+content);
	        out.writeBytes(content);
	        int responseCode = connection.getResponseCode();
//	        System.out.println("response:------------------" + responseCode);
            if (responseCode == 200) {
                // 取回响应的结果  
            	rt = changeInputStream(connection.getInputStream(), "UTF-8");
            	
        		PushDataXmlBean pdxb = new PushDataXmlBean();
    	        pdxb.setSampleid(tsp.getSampleid());
    	        pdxb.setSamplename(tsp.getSamplename());
    	        pdxb.setUri(tsp.getUri());
    	        pdxb.setCategory(tsp.getCategory().toString());
    	        pdxb.setLength(tsp.getLength().toString());
    	        pdxb.setPushtime(TimeUtil.getCurrentTime());
    	        pdxb.setReturnvalue(rt);
        		XmlUtils.createPushDataXml(pdxb);
        		
                return rt;
            }else{
            	rt = "";
            }
	        out.flush();
	        out.close();
	        
	        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	        String line;
	         
	        while ((line = reader.readLine()) != null){
	            System.out.println(line);
	        }
	        
	        reader.close();
	        connection.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rt;
	}
	
	 private static String changeInputStream(InputStream inputStream, String encode) {  
	     // 内存流  
	     ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();  
	     byte[] data = new byte[1024];  
         int len = 0;  
	     String result = null;  
	     if (inputStream != null) {  
	         try {  
	            while ((len = inputStream.read(data)) != -1) {  
	               byteArrayOutputStream.write(data, 0, len);  
	            }  
	            result = new String(byteArrayOutputStream.toByteArray(), encode);  
	         } catch (IOException e) {  
	             e.printStackTrace();  
	         }  
	     }  
	     return result;  
	 }  

	@Override
	public Integer updateTabSample(TabSample tabSample) {
		return tabSampleDao.updateTabSample(tabSample);
	}

	@Override
	public Integer deleteTabSample(String sampleId) {
		return tabSampleDao.deleteTabSample(sampleId);
	}

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
	public TabSample selectObjBySampleCode(String sampleCode) {
		return tabSampleDao.selectObjBySampleCode(sampleCode);
	}

	@Override
	public List<TabSample> selectUnpushTabSamples(TabSample tabSample) {
		return tabSampleDao.selectUnpushTabSamples(tabSample);
	}

	@Override
	public Integer getUnpushTotalCount(TabSample tabSample) {
		return tabSampleDao.getUnpushTotalCount(tabSample);
	}

}
