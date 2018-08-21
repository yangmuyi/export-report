package com.krm.test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.List;

import javax.sql.rowset.spi.XmlWriter;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.junit.Test;

public class Dom4jTest {

	//读取XML文档
	public static Document load(String filename){
		Document document = null;
		SAXReader saxReader = new SAXReader();
		try {
			// 读取XML文件，获得document对象
			document = saxReader.read(new File(filename));
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public static boolean doc2XmlFile(Document document,String filename){
		boolean flag = true;
		try {
			XMLWriter xmlWriter = new XMLWriter(new OutputStreamWriter(new FileOutputStream(filename),"UTF-8"));
			xmlWriter.write(document);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			flag = false;
			e.printStackTrace();
		}
		return flag;
	}
	
	//c:\Program Files\eclipse\UTF8\workspace\export-report\resources\report.xml
	@Test
	public void getRootElement(){
		String filename = "C:\\Program Files\\eclipse\\UTF8\\workspace\\export-report\\resources\\report.xml";
		Document dom = Dom4jTest.load(filename);
		Element root  = dom.getRootElement();
		String name = root.getName();
		System.out.println("根节点元素的名称："+name);
		
		// 打印根节点下的所有节点
		for (Iterator i= root.elementIterator() ; i.hasNext();) {
			Element el  = (Element) i.next();
			System.out.println(el.getName()+" "+el.getStringValue());;
		}
		
		// 查找指定的节点
		List  list= dom.selectNodes("/dayreport/condition");
		Iterator iterator = list.iterator();
		while(iterator.hasNext()){
			Element element = (Element) iterator.next();
				for(Iterator iterator2 = element.elementIterator();iterator2.hasNext();){
					Element element2 = (Element) iterator2.next();
					System.out.println(element2.getName()+" "+element2.getStringValue());;
				}
		}
	}
}
