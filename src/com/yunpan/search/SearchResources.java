package com.yunpan.search;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class SearchResources {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		CloseableHttpClient httpclient = HttpClients.createDefault();
		 HttpGet httpget = new HttpGet("http://api.ygyhg.com/pc/free?keyword=hello&order=feedtime&num=3");
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
		 
	}

}
