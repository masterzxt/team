package com.fight.dt.business.dao.conf;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by zhangwei on 17/7/11.
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@MapperScan(basePackages = "com.fight.dt.business.dao.mapper")
public class MybatisConfig {
    @Autowired
    private Environment env;
    @Autowired
    private MybatisProperties properties;

    @Bean
    public DataSource getDataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName", env.getProperty("spring.datasource.driver-class-name"));
        props.put("url", env.getProperty("spring.datasource.url"));
        props.put("username", env.getProperty("spring.datasource.username"));
        props.put("password", env.getProperty("spring.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        DataSource dataSource = getDataSource();
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        fb.setTypeAliasesPackage(env.getProperty("spring.mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(env.getProperty("spring.mybatis.mapper-locations")));
        return fb.getObject();
    }

    @Bean(name = "customTransactionManager")
    public DataSourceTransactionManager transactionManager(DataSource rdsDataSource) {
        return new DataSourceTransactionManager(rdsDataSource);
    }

}
