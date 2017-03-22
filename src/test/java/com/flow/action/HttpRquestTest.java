package com.flow.action;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import com.flow.action.network.HttpRequestAction;

public class HttpRquestTest {
	@Test
	public void httpRequestTest() throws Exception {
		HttpRequestAction httpRequest = new HttpRequestAction("Http Request Action", "http://www.baidu.com");
		String content = (String)httpRequest.execute();
		assertEquals(content.contains("http://news.baidu.com"), true);
	}
}
