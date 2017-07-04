package com.btvpyp.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;

import com.btvpyp.model.FeedbackXmlBean;
import com.btvpyp.model.MatchResultXmlBean;
import com.btvpyp.model.PushDataXmlBean;

public class XmlUtils {
	//调用北大接口推送样本数据的接口XML日志
	public static void createPushDataXml(PushDataXmlBean pdxb){
		Document doc = DocumentHelper.createDocument();
		Element pushdata = doc.addElement("pushdata");
		Element sampleid = pushdata.addElement("sampleid");
		Element samplename = pushdata.addElement("samplename");
		Element uri = pushdata.addElement("uri");
		Element category = pushdata.addElement("category");
		Element length = pushdata.addElement("length");
		Element returnvalue = pushdata.addElement("returnvalue");
		Element pushtime = pushdata.addElement("pushtime");
		
		sampleid.setText(pdxb.getSampleid());
		samplename.setText(pdxb.getSamplename());
		uri.setText(pdxb.getUri());
		category.setText(pdxb.getCategory());
		length.setText(pdxb.getLength());
		returnvalue.setText(pdxb.getReturnvalue());
		pushtime.setText(pdxb.getPushtime());
		
		String dir = TimeUtil.getTimeDir();
		String filePath = Commons.PUSHDATADIR + dir + "/" + pdxb.getSampleid() + "_push.xml";
		writeXml(filePath, doc);
	}
	
	public static void createMatchXml(MatchResultXmlBean mrxb){
		Document doc = DocumentHelper.createDocument();
		Element matchdata = doc.addElement("matchdata");
		Element sampleid = matchdata.addElement("sampleid");
		Element pid = matchdata.addElement("pid");
		Element starttime = matchdata.addElement("starttime");
		Element endtime = matchdata.addElement("endtime");
		Element matchtime = matchdata.addElement("matchtime");
		
		sampleid.setText(mrxb.getSampleid());
		pid.setText(mrxb.getPid());
		starttime.setText(mrxb.getStarttime());
		endtime.setText(mrxb.getEndtime());
		matchtime.setText(mrxb.getMatchtime());
		
		String dir = TimeUtil.getTimeDir();
		String filePath = Commons.MATCHLOGDIR + dir + "/" + mrxb.getSampleid()+"-" + mrxb.getStarttime() + "_match.xml";
		writeXml(filePath, doc);
	}
	
	public static void createFeedback(FeedbackXmlBean fbxb){
		Document doc = DocumentHelper.createDocument();
		Element sampleId = doc.addElement("sampleId");
		Element status = doc.addElement("status");
		Element feedbackTime = doc.addElement("feedbackTime");
		
		sampleId.setText(fbxb.getSampleid());
		status.setText(fbxb.getStatus());
		feedbackTime.setText(fbxb.getFeedbackTime());
		
		String dir = TimeUtil.getTimeDir();
		String filePath = "Commons.PUSHLOGDIR" + dir + "/" + fbxb.getSampleid() + "_feedback.xml";
		writeXml(filePath, doc);
		
	}
	
	public static void writeXml(String fileFullPath, Document doc){
		try {
			File saveFile = new File(fileFullPath);
			if (!saveFile.getParentFile().exists()){
				saveFile.getParentFile().mkdirs();
			}
			XMLWriter output = new XMLWriter(new FileWriter(saveFile));
			output.write(doc);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Document readXml(String path){
		SAXReader sr = new SAXReader();  //获取读取xml的对象
		Document doc = null;
		try {
			doc = sr.read(path);  //得到xml所在位置。然后开始读取。并将数据放入doc中
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		if(null != doc){
			return doc;
			
		}
		return doc;
	}
}
