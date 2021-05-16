/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mosmith.tools.report.website.storage.test;

import org.mosmith.tools.report.website.storage.utils.IOUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.mosmith.tools.report.website.storage.bootstrap.Bootstrap;

/**
 *
 * @author mosmith
 */
public class TemplateTest {
    
    private static final String URL_PREFIX="http://localhost:8080/templateManager";
    
    // @BeforeClass
    public static void beforeClass() {
        Bootstrap.main(new String[]{});
    }
    
    // @Test()
    public void testCreateTemplate() throws Exception {
        String id=createTemplate();
        deleteTemplate(id);
    }
    
    // @Test
    public void testGetTemplate() throws Exception {
        String id=createTemplate();
        
        Map<String, String> templateInfo = getTemplate(id);
        String data=templateInfo.get("data");
        
        System.out.println("Data: " + data);
        Assert.assertEquals("Not expected template data!", "<?xml version='1.0' encoding='utf-8' ?><root></root>", data);
    }
    
    // @Test
    public void testUpdateTemplate() throws Exception {
        String id=createTemplate();
        
        updateTemplate(id, "updated");
        
        Map<String, String> templateInfo = getTemplate(id);
        String data=templateInfo.get("data");
        
        System.out.println("Data: " + data);
        Assert.assertEquals("Not expected template data!", "updated", data);
    }
    
    // @Test
    public void testDeleteTemplate() throws Exception {
        String id=createTemplate();
        deleteTemplate(id);
        try {
            Map<String, String> templateInfo = getTemplate(id);
            throw new RuntimeException("Expected failure, but none!");
        } catch (Exception e) {
            // ok
        }
    }
    
    private String createTemplate() throws Exception {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(URL_PREFIX + "/createTemplate");
        
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("path", "/test.template"));
        nameValuePairs.add(new BasicNameValuePair("alias", "test"));
        nameValuePairs.add(new BasicNameValuePair("data", "<?xml version='1.0' encoding='utf-8' ?><root></root>"));
        
        httpPost.setHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8"));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));

        CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
        try {
            StatusLine statusline = httpResponse.getStatusLine();
            int statusCode=statusline.getStatusCode();
            Assert.assertEquals("Reponse code were not 200!", 200, statusCode);
            
            HttpEntity responseEntity=httpResponse.getEntity();
            InputStream is=responseEntity.getContent();
            
            String content=IOUtils.readAsString(is, "UTF-8");
            System.out.println("Response: " + content);
            
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String, String> responseTemplate=objectMapper.readValue(content, HashMap.class);
            String id=responseTemplate.get("id");
            return id;
        } finally {
            httpResponse.close();
        }
    }
    
    private void deleteTemplate(String id) throws Exception {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(URL_PREFIX + "/deleteTemplate");
        
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", id));
        
        httpPost.setHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8"));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        
        CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
        try {
            StatusLine statusLine=httpResponse.getStatusLine();
            int statusCode=statusLine.getStatusCode();
            Assert.assertEquals("Response was not 200!", 200, statusCode);
            
            HttpEntity responseEntity=httpResponse.getEntity();
            String responseContent=IOUtils.readAsString(responseEntity.getContent(), "UTF-8");
            
            System.out.println("Response: " + responseContent);
        } finally {
            httpResponse.close();
        }
    }
    
    private Map<String, String> getTemplate(String id) throws Exception {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        
        String url=URL_PREFIX + "/getTemplate";
        url=url + "?id=" + URLEncoder.encode(id, "UTF-8");
        
        HttpGet httpGet=new HttpGet(url);
        httpGet.setHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
        
        CloseableHttpResponse httpResponse=httpClient.execute(httpGet);
        try {
            StatusLine statusLine=httpResponse.getStatusLine();
            int statusCode=statusLine.getStatusCode();
            // Assert.assertEquals("Response code was not 200!", 200, statusCode);
            if(statusCode!=200) {
                throw new RuntimeException("Response code was not 200!");
            }
            
            HttpEntity httpEntity=httpResponse.getEntity();
            String responseContent=IOUtils.readAsString(httpEntity.getContent(), "UTF-8");
            System.out.println("Response: " + responseContent);
            
            ObjectMapper objectMapper=new ObjectMapper();
            Map<String, String> templateInfo = objectMapper.readValue(responseContent, HashMap.class);
            return templateInfo;
        } finally {
            httpResponse.close();
        }
    }
    

    private void updateTemplate(String id, String data) throws Exception {
        CloseableHttpClient httpClient=HttpClients.createDefault();
        HttpPost httpPost=new HttpPost(URL_PREFIX + "/updateTemplate");
        
        List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("id", id));
        nameValuePairs.add(new BasicNameValuePair("data", data));

        httpPost.setHeader(new BasicHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8"));
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
        
        CloseableHttpResponse httpResponse=httpClient.execute(httpPost);
        try {
            StatusLine statusLine=httpResponse.getStatusLine();
            int statusCode=statusLine.getStatusCode();
            Assert.assertEquals("Response was not 200!", 200, statusCode);
            
            HttpEntity responseEntity=httpResponse.getEntity();
            String responseContent=IOUtils.readAsString(responseEntity.getContent(), "UTF-8");
            
            System.out.println("Response: " + responseContent);
        } finally {
            httpResponse.close();
        }
    }
    
}
