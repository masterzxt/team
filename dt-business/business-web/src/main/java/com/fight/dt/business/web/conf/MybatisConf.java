package com.fight.dt.business.web.conf;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import com.fight.dt.business.dao.mapper.UserMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by zhangwei on 17/7/11.
 */
@Configuration
@MapperScan("com.fight.dt.business.dao.mapper")
@EnableTransactionManagement
public class MybatisConf implements TransactionManagementConfigurer {
    @Resource
    private Environment environment;

    @Autowired
    private DataSource dataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean fb = new SqlSessionFactoryBean();
        fb.setDataSource(dataSource);
        fb.setTypeAliasesPackage(environment.getProperty("spring.mybatis.type-aliases-package"));
        fb.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(environment.getProperty("spring.mybatis.mapper-locations")));
        return fb.getObject();
    }

    public PlatformTransactionManager annotationDrivenTransactionManager(){
        return new DataSourceTransactionManager(dataSource);
    }

}
