package com.rightprice.auth.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
public class MSSqlConfiguration {
	
	@Bean(name = "mssqlDb")
	@ConfigurationProperties(prefix = "spring.ds_mssql")
	public DataSource mysqlDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "mssqlJdbcTemplate")
	public JdbcTemplate jdbcTemplate(@Qualifier("mssqlDb") DataSource dsMSSQL) {
		return new JdbcTemplate(dsMSSQL);
	}
}
