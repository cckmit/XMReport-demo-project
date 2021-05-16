/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.mosmith.tools.report.website.storage.config;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 *
 * @author Administrator
 */
@Configuration
public class PersistenceConfiguration {

    @Bean
    public PlatformTransactionManager getTransactionManager() {
        JpaTransactionManager jpaTm=new JpaTransactionManager();
        return jpaTm;
    }
    
    @Bean
    public LocalEntityManagerFactoryBean getLocalEntityManagerFactoryBean() {
        LocalEntityManagerFactoryBean localEntityManagerFactoryBean = new LocalEntityManagerFactoryBean();
        localEntityManagerFactoryBean.setPersistenceUnitName("TemplateStorage");
        return localEntityManagerFactoryBean;
    }
    
}
