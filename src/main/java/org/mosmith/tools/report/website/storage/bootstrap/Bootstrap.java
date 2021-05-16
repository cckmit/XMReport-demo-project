/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mosmith.tools.report.website.storage.bootstrap;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

/**
 *
 * @author Administrator
 */

@SpringBootApplication(scanBasePackages={"org.mosmith.tools.report.website.storage"})
public class Bootstrap {
    public static void main(String[] args) {
        String derbySystemHome=System.getenv("DERBY_SYSTEM_HOME");
        if(derbySystemHome!=null) {
            System.setProperty("derby.system.home", derbySystemHome);
        }
        SpringApplication.run(Bootstrap.class, args);
    }
}
