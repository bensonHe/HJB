package org.hjb.component;

import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public class LUTest {
	public static void main(String[] args) {
		HttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("https://www.baidu.com");
	}
}
