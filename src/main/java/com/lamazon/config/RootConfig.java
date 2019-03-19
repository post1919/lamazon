package com.lamazon.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.StdDateFormat;

/*
 * 애플리케이션 컨텍스트
 */

@Configuration
//@ComponentScan("com.lamazon")
@EnableTransactionManagement
@MapperScan("com.lamazon.mapper")
@EnableAspectJAutoProxy //AOP 설정
@Import({PropertiesConfig.class})
public class RootConfig {

	@Value("${datasource.driverClassName}") String driverClassName;
	@Value("${datasource.url}") String url;
	@Value("${datasource.username}") String username;
	@Value("${datasource.password}") String password;
	@Value("${datasource.max-total}") int maxActive;

	//공통 dataSource
	@Bean(destroyMethod="close")
	DataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		dataSource.setDriverClassName(driverClassName);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		dataSource.setMaxActive(maxActive);
		dataSource.setDefaultAutoCommit(false);
		return dataSource;
	}

	//트랜잭션 설정
	@Bean
	public PlatformTransactionManager transactionManager() {
		return new DataSourceTransactionManager(dataSource());
	}

	//MyBatis 설정
	@Bean
	public SqlSessionFactoryBean sqlSessionFactory() {
		SqlSessionFactoryBean sessionFactoryBean = new SqlSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setConfigLocation(new ClassPathResource("/lamazon_mybatis_config.xml"));
		return sessionFactoryBean;
	}

	//JSON에 들여쓰기와 개행을 포함하도록 Jackson 설정
	//Date and Time API 클래스 사용추가
	@Bean
	ObjectMapper objectMapper() {
		return Jackson2ObjectMapperBuilder
				.json()
				.indentOutput(true)
				.dateFormat(new StdDateFormat())
				.build()
				;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10);
		return multipartResolver;
	}
}