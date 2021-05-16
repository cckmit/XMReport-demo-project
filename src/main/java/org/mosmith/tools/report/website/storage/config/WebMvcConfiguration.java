/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mosmith.tools.report.website.storage.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 *
 * @author mosmith
 */
@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**")
            .addResourceLocations("file:///D:/programcode/web_report_git/web-report/dist/")
            .addResourceLocations("file:///D:/programcode/web_report_git/web-form/dist/")
            .addResourceLocations("file:/xmreport-demo-data/")
            .addResourceLocations("classpath:/static/");
    }
    
}
