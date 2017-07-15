package com.fight.dt.business.web.conf;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.fight.dt.business.dao.mapper.UserMapper;
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
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by zhangwei on 17/7/11.
 */
@Configuration
@EnableConfigurationProperties(MybatisProperties.class)
@MapperScan(basePackageClasses = {UserMapper.class})
@EnableTransactionManagement
public class MybatisConf {
    @Resource
    private Environment environment;
    @Resource
    private DataSource dataSource;

    @Bean
    public DataSource dataSource() throws Exception{
        Properties props = new Properties();
        props.put("driverClassName", environment.getProperty("spring.datasource.driver-class-name"));
        props.put("url", environment.getProperty("spring.datasource.url"));
        props.put("username", environment.getProperty("spring.datasource.username"));
        props.put("password", environment.getProperty("spring.datasource.password"));
        return DruidDataSourceFactory.createDataSource(props);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        fb.setTypeAliasesPackage(environment.getProperty("spring.mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(environment.getProperty("spring.mybatis.mapper-locations")));
        return fb.getObject();
    }

    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource);
    }

}
