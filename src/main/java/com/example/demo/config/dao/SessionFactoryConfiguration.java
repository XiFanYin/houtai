package com.example.demo.config.dao;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScanPackages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;
import javax.validation.Valid;
import java.io.IOException;
//配置mybatis属性
@Configuration
public class SessionFactoryConfiguration {

    @Value("${mybatis_config_file}")
    private String mybatisConfigFilePath;
    @Value("${mapper_path}")
    private String mapperPath;
    @Autowired
    @Qualifier("dataSource")
    private DataSource dataSource;
    @Value("${entity_package}")
    private String entityPackages;

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactoryBean createSqlSessionFactoryBean() throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        //设置mybatis配置文件路径
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource(mybatisConfigFilePath));
        //设置sql语句的映射文件
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        String path = PathMatchingResourcePatternResolver.CLASSPATH_ALL_URL_PREFIX + mapperPath;
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources(path));
        //配置DataSourse
        sqlSessionFactoryBean.setDataSource(dataSource);
        //配置映射实体类路径
        sqlSessionFactoryBean.setTypeAliasesPackage(entityPackages);

        return sqlSessionFactoryBean;
    }


}
