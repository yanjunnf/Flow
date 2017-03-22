package com.flow.action.network;

import java.util.logging.Logger;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.flow.action.AbstractAction;

/*
 * Get static HTML content from specified URL
 * */
public class HttpRequestAction extends AbstractAction {
	private String url;
	private int timeout = 120;
	private Connection connection;
	private Logger logger = Logger.getLogger(HttpRequestAction.class.toString());
	
	public HttpRequestAction(String name, String url) {
		super(name, false);
		this.url = url;
		initialize();
	}
	
	private void initialize() {
		connection = Jsoup.connect(url).header("Accept-Charset", "GB2312,utf-8;q=0.7,*;q=0.7")
				.header("Accept-Language", "en,zh-CN;q=0.8,zh;q=0.6,en-US")
				.ignoreHttpErrors(true).ignoreContentType(true).timeout(timeout);
	}

	@Override
	public Object execute() throws Exception {
		String content = "";
		try {
			Document document = connection.get();
			content = document.toString();
		} catch (Exception e) {
			logger.severe("Failed to send http request. Message=" + e.getMessage());
			throw e;
		}
		return content;
	}
	
	public void setUserAgent(String userAgent) {
		connection.userAgent(userAgent);
	}
	
	public void addHeader(String key, String value) {
		connection.header(key, value);
	}
}
