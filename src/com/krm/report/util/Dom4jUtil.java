package com.krm.report.util;

import java.io.File;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.krm.report.vo.AdditionalVo;
import com.krm.report.vo.ConditionVo;
import com.krm.report.vo.FormVo;
import com.krm.report.vo.OrganVo;

public class Dom4jUtil {
	private static Logger log =LoggerFactory.getLogger(Dom4jUtil.class);
	/**
	 * 获取所有机构信息
	 * @return
	 */
	public static List<OrganVo> getOrganVoList(){
		Document doc = null;
		List<OrganVo> list = new ArrayList<OrganVo>();
		try {
 
			// 读取并解析XML文档
 
			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来
 
			 SAXReader reader = new SAXReader(); //organ.xml表示你要解析的xml文档
			//文件在class的根路径
			InputStream is=Dom4jUtil.class.getClassLoader().getResourceAsStream("organ.xml");
			 doc = reader.read(is);
 
			// 下面的是通过解析xml字符串的
			 
//			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
 
			Element rootElt = doc.getRootElement(); // 获取根节点
 
			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
 
			Iterator<Element> it = rootElt.elementIterator("organ");// 获取根节点下所有content
			while (it.hasNext()) {
				Element elementGroupService = (Element) it.next();
				OrganVo baseBean = (OrganVo) XmlUtil.fromXmlToBean(
						elementGroupService, OrganVo.class);
				list.add(baseBean);
			}
 
		} catch (Exception e) {
			// TODO: handle exception
			log.error("数据解析错误");
		}
 
		return list;
	}
	
	public static void main(String[] args) {
//		List<OrganVo> list = getOrganVoList();
//		for (OrganVo organVo : list) {
//			System.out.println(organVo.getId()+","+organVo.getName());
//		}
//		System.out.println(list.size());
//		System.out.println(getFormVo());
//		FormVo formVo = getFormVo();
//		List<ConditionVo> list = formVo.getCondition();
//		String[] reslut = ListToStringUtil.conditionToString(list);
//		for (String string : reslut) {
//			System.out.println(string);
//		}
//		List<AdditionalVo> list2  = formVo.getAdditional();
//		String[] resultStrings = ListToStringUtil.additionalToString(list2);
//		for (String string : resultStrings) {
//			System.out.println(string);
//		}
		
//		File file = new File("resources/report/");
//		if(file.isDirectory()){
//			File[] files = file.listFiles();
//			System.out.println(files[0].getName());
//		}
		
		
		//获取文件夹下的所有文件
		List<FormVo> list = getFormVoList("1","seasonreport");
		for (FormVo formVo : list) {
			System.out.println(formVo.getReportId());
		}
		
	}
	
	public static FormVo getFormVo(){
		Document doc = null;
		FormVo formVo = new FormVo();
		try {
			// 读取并解析XML文档
			 
						// SAXReader就是一个管道，用一个流的方式，把xml文件读出来
			 
						 SAXReader reader = new SAXReader(); //organ.xml表示你要解析的xml文档
						//文件在class的根路径
						InputStream is=Dom4jUtil.class.getClassLoader().getResourceAsStream("report.xml");
						 doc = reader.read(is);
			 
						// 下面的是通过解析xml字符串的
						 
//					doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			 
						Element rootElt = doc.getRootElement(); // 获取根节点
			 
						System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
						
						String sortCol = rootElt.elementTextTrim("sortCol");
						String sortOrder = rootElt.elementTextTrim("sortOrder");
						String reportId = rootElt.elementTextTrim("reportId");
						String chartChange = rootElt.elementTextTrim("chartChange");
						String chartType = rootElt.elementTextTrim("chartType");
						String chartSortType = rootElt.elementTextTrim("chartSortType");
						String sortField = rootElt.elementTextTrim("sortField");
						String queryType = rootElt.elementTextTrim("queryType");
						
						Iterator<Element> it = rootElt.elementIterator("condition");// 获取根节点下所有content
						List conditionList = new ArrayList<ConditionVo>();
						while (it.hasNext()) {
							Element elementGroupService = (Element) it.next();
							ConditionVo baseBean = (ConditionVo) XmlUtil.fromXmlToBean(
									elementGroupService, ConditionVo.class);
							conditionList.add(baseBean);
						}
						
						
						Iterator<Element> it2 = rootElt.elementIterator("additional");// 获取根节点下所有content
						List additionalList = new ArrayList<AdditionalVo>();
						while (it2.hasNext()) {
							Element elementGroupService = (Element) it2.next();
							AdditionalVo baseBean = (AdditionalVo) XmlUtil.fromXmlToBean(
									elementGroupService, AdditionalVo.class);
							additionalList.add(baseBean);
						}
						
						formVo.setSortCol(sortCol);
						formVo.setSortOrder(sortOrder);
						formVo.setReportId(reportId);
						formVo.setChartChange(chartChange);
						formVo.setChartType(chartType);
						formVo.setChartSortType(chartSortType);
						formVo.setSortField(sortField);
						formVo.setSortField(sortField);
						formVo.setQueryType(queryType);
						formVo.setCondition(conditionList);
						formVo.setAdditional(additionalList);
		} catch (Exception e) {
			// TODO Auto-generated catch block
//			e.printStackTrace();
			log.error(e.getMessage());
		}
		
		return formVo;
	}
	/**
	 * 根据文件名称获取报表表单参数
	 * @param filename
	 * @return
	 */
	public static FormVo getFormVo(String filename){
		Document doc = null;
		FormVo formVo = new FormVo();
		try {
			// 读取并解析XML文档
			
			// SAXReader就是一个管道，用一个流的方式，把xml文件读出来
			
			SAXReader reader = new SAXReader(); //organ.xml表示你要解析的xml文档
			//文件在class的根路径
			InputStream is=Dom4jUtil.class.getClassLoader().getResourceAsStream(filename);
			doc = reader.read(is);
			
			// 下面的是通过解析xml字符串的
//			doc = DocumentHelper.parseText(xml); // 将字符串转为XML
			
			Element rootElt = doc.getRootElement(); // 获取根节点
			
//			System.out.println("根节点：" + rootElt.getName()); // 拿到根节点的名称
			
			String sortCol = rootElt.elementTextTrim("sortCol");
			String sortOrder = rootElt.elementTextTrim("sortOrder");
			String reportId = rootElt.elementTextTrim("reportId");
			String chartChange = rootElt.elementTextTrim("chartChange");
			String chartType = rootElt.elementTextTrim("chartType");
			String chartSortType = rootElt.elementTextTrim("chartSortType");
			String sortField = rootElt.elementTextTrim("sortField");
			String queryType = rootElt.elementTextTrim("queryType");
			
			Iterator<Element> it = rootElt.elementIterator("condition");// 获取根节点下所有content
			List conditionList = new ArrayList<ConditionVo>();
			while (it.hasNext()) {
				Element elementGroupService = (Element) it.next();
				ConditionVo baseBean = (ConditionVo) XmlUtil.fromXmlToBean(
						elementGroupService, ConditionVo.class);
				conditionList.add(baseBean);
			}
			
			
			Iterator<Element> it2 = rootElt.elementIterator("additional");// 获取根节点下所有content
			List additionalList = new ArrayList<AdditionalVo>();
			while (it2.hasNext()) {
				Element elementGroupService = (Element) it2.next();
				AdditionalVo baseBean = (AdditionalVo) XmlUtil.fromXmlToBean(
						elementGroupService, AdditionalVo.class);
				additionalList.add(baseBean);
			}
			
			formVo.setSortCol(sortCol);
			formVo.setSortOrder(sortOrder);
			formVo.setReportId(reportId);
			formVo.setChartChange(chartChange);
			formVo.setChartType(chartType);
			formVo.setChartSortType(chartSortType);
			formVo.setSortField(sortField);
			formVo.setSortField(sortField);
			formVo.setQueryType(queryType);
			formVo.setCondition(conditionList);
			formVo.setAdditional(additionalList);
		} catch (Exception e) {
//			e.printStackTrace();
			log.error(filename);
		}
		
		return formVo;
	}
	
	/**
	 * 获取指定路径下的所有报表xml
	 * @param path
	 * @return
	 */
	public static List<FormVo> getFormVoList(String path){
		List<FormVo> formVoList  = new ArrayList<FormVo>();
		File[] files = null;
		File file = new File("");
		log.info("***folder resource path***:"+file.getAbsolutePath());
		String absolutePath = file.getAbsolutePath();
		String parent = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
		File file1 = new File(parent+path);
		log.info("***folder config path***:"+file1.getAbsolutePath());
		if(file1.isDirectory()){
			files = file1.listFiles();
		}
		if(files==null || files.length==0){
			return formVoList;
		}
		for (File file2 : files) {
			String temp = path.substring(path.lastIndexOf("/")+1, path.length());
			String filename = temp+"/"+file2.getName();
//			log.info("导出的报表名称："+filename);
			formVoList.add(getFormVo(filename));
		}
		return formVoList;
	}
	/**
	 * 获取指定路径下的所有报表xml
	 * @param system
	 * @param path
	 * @return
	 */
	public static List<FormVo> getFormVoList(String system,String path){
		List<FormVo> formVoList  = new ArrayList<FormVo>();
		if(system.equals("win")){
			File[] files = null;
			File file = new File("resources/"+path);
			log.info("文件夹路径resource："+file.getAbsolutePath());
			if(file.isDirectory()){
				files = file.listFiles();
			}
			if(files==null || files.length==0){
				return formVoList;
			}
			for (File file2 : files) {
				String filename = path+"/"+file2.getName();
//			log.info("导出的报表名称："+filename);
				formVoList.add(getFormVo(filename));
			}
		}
		if(system.equals("linux")){
			File[] files = null;
			File file = new File("");
			log.info("***folder resource path***:"+file.getAbsolutePath());
			String absolutePath = file.getAbsolutePath();
			String parent = absolutePath.substring(0, absolutePath.lastIndexOf("/"));
			File file1 = new File(parent+path);
			log.info("***folder config path***:"+file1.getAbsolutePath());
			if(file1.isDirectory()){
				files = file1.listFiles();
			}
			if(files==null || files.length==0){
				return formVoList;
			}
			for (File file2 : files) {
				String temp = path.substring(path.lastIndexOf("/")+1, path.length());
				String filename = temp+"/"+file2.getName();
//				log.info("导出的报表名称："+filename);
				formVoList.add(getFormVo(filename));
			}
		}
		return formVoList;
	}
}
