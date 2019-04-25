package com.example.demo.config.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;

/*配合事物管理让数据实现回滚管理
 *
 *  @Transactional(propagation = Propagation.REQUIRED)配置改变
 *
 *  @Transactional(propagation = Propagation.SUPPORTS)配置查询
 * */
@Configuration
@EnableTransactionManagement
public class TransactionManagementConfiguration implements TransactionManagementConfigurer {
    @Autowired
    private DataSource dataSource;

    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {

        return new DataSourceTransactionManager(dataSource);
    }
}
