package com.filename.extension.main.dao;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.filename.extension.config.DataSourceConfig;
import com.filename.extension.main.domain.MainDomain;

public class MainDaoTest {
	
	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(DataSourceConfig.class);
		MainDao dao = context.getBean("mainDao",MainDao.class);
	}
	
}
