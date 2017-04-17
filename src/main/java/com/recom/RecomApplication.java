package com.recom;

import com.recom.utils.DatabaseType;
import com.recom.utils.DynamicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan
@MapperScan("com.recom.dao")
public class RecomApplication {

	//配置dataSource
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource1")
	public org.apache.tomcat.jdbc.pool.DataSource dataSource1() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	//配置dataSource
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource2")
	public org.apache.tomcat.jdbc.pool.DataSource dataSource2() {
		return new org.apache.tomcat.jdbc.pool.DataSource();
	}

	@Bean
	@Primary
	public DynamicDataSource dataSource(@Qualifier("dataSource1") org.apache.tomcat.jdbc.pool.DataSource dataSource1,
										@Qualifier("dataSource2") org.apache.tomcat.jdbc.pool.DataSource dataSource2) {
		Map<Object, Object> targetDataSources = new HashMap<>();
		targetDataSources.put(DatabaseType.iteach_cernet, dataSource1);
		targetDataSources.put(DatabaseType.iteach_recom, dataSource2);

		DynamicDataSource dataSource = new DynamicDataSource();
		dataSource.setTargetDataSources(targetDataSources);// 该方法是AbstractRoutingDataSource的方法
		dataSource.setDefaultTargetDataSource(dataSource1);// 默认的datasource设置为myTestDbDataSource

		return dataSource;
	}

	//sqlSession
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource ds) throws Exception {
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(ds);
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mybatis/*.xml"));
		return sqlSessionFactoryBean.getObject();
	}

	//事务
	@Bean
	public PlatformTransactionManager transactionManager(DynamicDataSource ds) {
		return new DataSourceTransactionManager(ds);
	}


	public static void main(String[] args) {
		SpringApplication.run(RecomApplication.class, args);
	}
}
