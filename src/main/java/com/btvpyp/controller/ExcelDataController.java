package com.btvpyp.controller;

import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.btvpyp.model.TabSample;
import com.btvpyp.model.TabUser;
import com.btvpyp.service.TabSampleService;
import com.btvpyp.utils.ImportExcelUtil;

@Controller
@RequestMapping("/uploadExcel")
public class ExcelDataController {
	
	@Autowired
	private TabSampleService tabSampleService;
	
	@Autowired
	private HttpServletRequest httpRequest;
	
	@RequestMapping(value="toImportSamples",method=RequestMethod.GET)
	public String toImportSamples(){
		return "pyp/batchImport";
	}
	/**
	 * 从Excel批量导入样本数据
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value="importExcel",method=RequestMethod.POST)
	public String uploadExcel(HttpServletRequest request) throws Exception {
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		HttpSession session = httpRequest.getSession();
		TabUser tUser = (TabUser)session.getAttribute("tabUser");
		
		InputStream in =null;
		List<List<Object>> listob = null;
		MultipartFile file = multipartRequest.getFile("upfile");
		if(file.isEmpty()){  
	        throw new Exception("文件不存在！");  
	    }
		in = file.getInputStream();
		listob = new ImportExcelUtil().getDataByExcel(in,file.getOriginalFilename());
		in.close();
		
		if(null != listob){
			if(listob.size()>0){
				//解析listob，填装对象字段值
				for (int i = 0; i < listob.size(); i++) {
					List<Object> lo = listob.get(i);
					TabSample t = setObjValue(lo);
					if(null != t){
						t.setCreaterName(tUser.getUserName());
						t.setCreateTime(new Timestamp(System.currentTimeMillis()));
						t.setIsAdv(1);
						
						String sampleId = tabSampleService.insertTabSample2(t);
					}
				}
			}
		}
		return "result";
	}
	
	public TabSample setObjValue(List<Object> lo){
		TabSample ts = new TabSample();
		//第一列：主名称
		String mainName = String.valueOf(lo.get(0));
		if(null == mainName || "".equals(mainName)){
			return null;
		}else{
			ts.setMainName(mainName);
		}
		
		//第二列：副名称
		ts.setSecondName(String.valueOf(lo.get(1)));
		//第三列：播放日期
		ts.setSampleDate(String.valueOf(lo.get(2)));
		//第四列：频道
		ts.setPid(String.valueOf(lo.get(3)));
		//第五列：时长
		ts.setLength(Integer.parseInt(String.valueOf(lo.get(4))));
		//第六列：磁带号
//		ts.setTapeNum(String.valueOf(lo.get(5)));
		//第七列：栏目类型
		ts.setColumnType(String.valueOf(lo.get(5)));
		//第八列：广告id
		ts.setAdvId(String.valueOf(lo.get(6)));
		//第九列：代理商
		ts.setCompany(String.valueOf(lo.get(7)));
		//第十列：文件存放位置(共享目录)
		ts.setFileAddr(String.valueOf(lo.get(8)));
		//第十一列：文件链接地址(外网)
		ts.setFileNetAddr(String.valueOf(lo.get(9)));
		//预置分类值(给北大)
		ts.setMiddleType("8");
		ts.setFlag(1);
		ts.setExtFlag(0);
		return ts;
	}
	
	@ResponseBody  
    @RequestMapping(value="/ajaxUpload",method=RequestMethod.POST)  
    public void ajaxUploadExcel(HttpServletRequest request,HttpServletResponse response) throws Exception {  
        
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;    
//        System.out.println("通过 jquery.form.js 提供的ajax方式上传文件！");  
          
        InputStream in =null;  
        List<List<Object>> listob = null;  
        MultipartFile file = multipartRequest.getFile("upfile");  
        if(file.isEmpty()){  
            throw new Exception("文件不存在！");  
        }  
          
        in = file.getInputStream();  
        listob = new ImportExcelUtil().getDataByExcel(in,file.getOriginalFilename());  
          
        //该处可调用service相应方法进行数据保存到数据库中，现只对数据输出  
        for (int i = 0; i < listob.size(); i++) {  
            List<Object> lo = listob.get(i);  
        }  
          
        PrintWriter out = null; 
        response.setCharacterEncoding("utf-8");  //防止ajax接受到的中文信息乱码  
        out = response.getWriter();  
        out.print("文件导入成功！");  
        out.flush();  
        out.close();  
    }
}
