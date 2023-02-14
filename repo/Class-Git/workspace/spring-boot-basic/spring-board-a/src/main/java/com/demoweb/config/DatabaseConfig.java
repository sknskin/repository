package com.demoweb.config;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration // -> servlet-context.xml 과 같은 설정파일 클래스로 구현
@PropertySource("classpath:/application.properties")
public class DatabaseConfig {
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public HikariConfig hikariConfig() {
		
		HikariConfig config = new HikariConfig();
//		config.setDriverClassName("com.mysql.cj.jdbc.Driver");
//		config.setJdbcUrl("jdbc:mysql://localhost:3306/demoweb");
//		config.setUsername("testuser");
//		config.setPassword("mysql");
		
		return config;
	}
	
	@Bean // --> servlet-context.xml과 같은 설정 파일의 <bean에 해당
	public DataSource dataSource() {		
		HikariDataSource dataSource = new HikariDataSource(hikariConfig());
		
		return dataSource;
	}
	
	////////////////////////////////////////
	
	@Bean
	@ConfigurationProperties(prefix = "mybatis.configuration")
	public org.apache.ibatis.session.Configuration mybatisConfig() {
		
		org.apache.ibatis.session.Configuration config = new org.apache.ibatis.session.Configuration();
		return config;
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setConfiguration(mybatisConfig());
		factoryBean.setDataSource(dataSource());
		
		SqlSessionFactory factory = factoryBean.getObject();
		return factory;
		
		
	}
	
	
	

}
















