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
public class BootstrapTest {
    
    private static final String URL_PREFIX="http://localhost:8080/templateManager";
    
    // @BeforeClass
    public static void beforeClass() {
        Bootstrap.main(new String[]{});
    }
    
    // @Test()
    public void testCreateTemplate() throws Exception {
        // Thread.sleep(1000*1000);
    }
}
