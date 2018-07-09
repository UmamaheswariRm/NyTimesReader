package com.sample.nytimes;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import org.xml.sax.SAXException;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;


public class NyTimesApi_Params {
	public static final String OUTPUT_XML = "json";//change the format here
	private String outputMode = OUTPUT_XML;
	private String url;
	
	public String getUrl() {
		return url;
	}
	
	public void setUrl(String url) {
		this.url = url;
	}
	
	public String getOutputMode() {
		return outputMode;
	}
	
	public static void main(String[] args)
			throws IOException, SAXException,
			ParserConfigurationException, XPathExpressionException
		{
			// Create an AlchemyAPI object.
			NyTimesAPI alchemyObj = new NyTimesAPI();
		
			String obj = alchemyObj.URLGetAuthor("all-sections/7.json");
			//Printed as a String, but can be converted to the reqd format using proper libraries
			System.out.println(obj);
		}
	
	public String getParameterString(){
		String retString = "";
		try{
			if(url!=null) retString+="&url="+URLEncoder.encode(url,"UTF-8");
//			if(outputMode!=null) retString+="&outputMode="+outputMode;
		}
		catch(UnsupportedEncodingException e ){
			retString = "";
		}
			return retString;
	}

}