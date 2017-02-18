package com.yunpan.service.impl;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import com.yunpan.entity.SearchConstants;
import com.yunpan.service.SearchResourceService;

public class SearchResourceServiceImpl implements SearchResourceService {

	@Override
	public String searchOrderByTime(String keyWord) {
		String res = "";
		res =  searchOrderByTime(keyWord,SearchConstants.LIMITATION_MAX);
		return res;
	}

	@Override
	public String searchOrderByTime(String keyWord, String limitation) {
		String res = "";

		StringBuffer sb = new StringBuffer(SearchConstants.REQUEST_URL);
		CloseableHttpClient httpclient = HttpClients.createDefault();
		if(limitation == null || limitation == ""){
			limitation = SearchConstants.LIMITATION_MAX;
		}
		sb.append(keyWord).append(SearchConstants.ORDER).append(SearchConstants.LIMITATION)
				.append(limitation);
		System.out.println(sb);
		HttpGet httpget = new HttpGet(sb.toString());
		System.out.println("executing request " + httpget.getURI());
		CloseableHttpResponse response = null;
		try {
			response = httpclient.execute(httpget);
			HttpEntity entity = response.getEntity();
			System.out.println("--------------------------------------");
			System.out.println(response.getStatusLine());
			if (entity != null) {
				// 打印响应内容长度
				System.out.println("Response content length: " + entity.getContentLength());
				// 打印响应内容
				System.out.println("Response content: " + EntityUtils.toString(entity));
				res = entity.toString();
				System.out.println("------------------------------------");
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				response.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return res;
	}
	
	public static void main(String[] args) {
		
		SearchResourceServiceImpl test = new SearchResourceServiceImpl();
		test.searchOrderByTime("hello","");
	}

}
