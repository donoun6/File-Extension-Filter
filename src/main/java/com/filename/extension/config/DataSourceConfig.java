package com.filename.extension.config;

import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import com.filename.extension.main.dao.MainDao;


@Configuration
public class DataSourceConfig {
	
	@Bean(destroyMethod = "close")
	public DataSource dataSource() {
		//mysql db
		DataSource ds = new DataSource();
		ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
		ds.setUrl("jdbc:mysql://localhost:3306/extension?serverTimezone=Asia/Seoul");
		ds.setUsername("extension");
		ds.setPassword("extension");
		ds.setInitialSize(2);	// 커넥션 풀 초기화시 생성할 초기 커넥션 개수(기본값 10)
		ds.setMaxActive(10);	// 풀에서 가져올 수 있는 최대 커넥션 개수(기본값 100)
		ds.setMaxIdle(10);		// 풀에 유지할 수 있는 최대 커넥션 수(기본값은 MaxActive와 동일)
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager txManager = new DataSourceTransactionManager();
		txManager.setDataSource(dataSource());
		return txManager;
	}
	
	@Bean
	public JdbcTemplate jdbcTemplate() {
		return new JdbcTemplate(dataSource());
	}
	
//	main test bean name 등록 (@Autowired 를 main에서 사용이 안돼서 혹시나 아시는분 말씀좀!)
	@Bean
	public MainDao mainDao() {
		return new MainDao(dataSource());
	}
}
