package com.flow.action.network;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import com.flow.action.AbstractAction;

/*
 * This action is used for call Restful API
 * Only support two methods GET and POST.
 * For supporting upload attachment, is coming soon. 
 * */
public class RestInvokerAction extends AbstractAction {
	private String method;
	private String url;
	private String accept;
	private String contentType;
	private String charset;
	//Input only used in POST method
	private String input;
	private int responseCode;
	private Logger logger = Logger.getLogger(RestInvokerAction.class.toString());
	
	public RestInvokerAction(String name, String url, String method) {
		super(name, false);
		this.url = url;
		this.method = method;
		this.accept = "application/json";
		this.contentType = "application/json";
		this.charset = "UTF-8";
		this.responseCode = 500;
	}

	@Override
	public Object execute() throws Exception {
		if (method.equals("GET"))
			return get();
		else if (method.equals("POST"))
			return post();
		
		return null;
	}
	
	private String get() throws Exception {
		StringBuffer content = new StringBuffer();
		try {
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();
			connection.setDoOutput(true);
			connection.setRequestMethod(method);
			connection.setRequestProperty("accpet", accept);
			responseCode = connection.getResponseCode();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line = "";
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
			
			connection.disconnect();
			
		} catch (Exception e) {
			logger.severe("Failed to invoke rest api. url=" + url + ", message=" + e.getMessage());
			throw e;
		}
		
		return content.toString();
	}
	
	private String post() throws Exception {
		StringBuffer content = new StringBuffer();
		try {
			URL urlObj = new URL(url);
			HttpURLConnection connection = (HttpURLConnection)urlObj.openConnection();
			connection.setRequestMethod(method);
			connection.setRequestProperty("accpet", accept);
			connection.setRequestProperty("Content-Type", contentType);
			responseCode = connection.getResponseCode();
			
			OutputStream os = connection.getOutputStream();
			os.write(input.getBytes());
			os.flush();
			
			responseCode = connection.getResponseCode();
			
			BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), charset));
			String line = "";
			while ((line = br.readLine()) != null) {
				content.append(line);
			}
			
			connection.disconnect();
			
		} catch (Exception e) {
			logger.severe("Failed to invoke rest api. url=" + url + ", message=" + e.getMessage());
			throw e;
		}
		
		return content.toString();
	}
	
	public void setAccept(String accept) {
		this.accept = accept;
	}
	
	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public int getResponseCode() {
		return responseCode;
	}
	
	public void setInput(String input) {
		this.input = input;
	}
}
