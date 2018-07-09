package com.sample.nytimes;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


public class NyTimesAPI {
	
	// If running locally complete the variables below with the information in VCAP_SERVICES
	private static String baseURL = "http://api.nytimes.com/svc/mostpopular/v2/mostviewed/";
	private static String key = "13447efbecde494d9229452c945218af";
	 
	public String URLGetAuthor(String url)
			throws IOException, SAXException,
			ParserConfigurationException, XPathExpressionException
		{
			return GET(url, new NyTimesApi_Params());
		}
	

	private String GET(String callName, NyTimesApi_Params params)
			throws IOException, SAXException,
			ParserConfigurationException, XPathExpressionException
		{
			StringBuilder uri = new StringBuilder();
			uri.append(baseURL).append(callName)
			.append('?').append("apikey=").append(key);
			
			System.out.println("URI = " + uri);
			URL url = new URL(uri.toString());
			HttpURLConnection handle = (HttpURLConnection) url.openConnection();
			handle.setDoOutput(true);
			return doRequest(handle, params.getOutputMode());
		}
	
	private String doRequest(HttpURLConnection handle, String outputMode)
			throws IOException
			{
				try{
				    int status = handle.getResponseCode();
				    switch (status) {
			            case 200:
			            case 201:
			                BufferedReader br = new BufferedReader(new InputStreamReader(handle.getInputStream()));
			                StringBuilder sb = new StringBuilder();
			                String line;
			                while ((line = br.readLine()) != null) {
			                    sb.append(line+"\n");
			                }
			                br.close();
			                return sb.toString();
			              
			        }
					}
				catch(IOException ex){
						System.out.println("IO Exception ");
					}
				return "";
			}
}