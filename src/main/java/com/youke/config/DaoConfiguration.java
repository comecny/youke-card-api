package com.youke.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableTransactionManagement
@MapperScan("com.youke.dao")
public class DaoConfiguration {

    @Bean
    @ConfigurationProperties("spring.datasource")
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        List<Filter> filters = new ArrayList<>();
        filters.add(statFilter());
        filters.add(wallFilter());
        dataSource.setProxyFilters(filters);
        return dataSource;
    }

    @Bean
    public StatFilter statFilter() {
        StatFilter statFilter = new StatFilter();
        statFilter.setSlowSqlMillis(3000);
        statFilter.setLogSlowSql(true);
        statFilter.setMergeSql(true);
        return statFilter;
    }

    @Bean
    @ConfigurationProperties("druid.filter.wall")
    public WallFilter wallFilter() {
        WallFilter wallFilter = new WallFilter();
        wallFilter.setConfig(wallConfig());
        return wallFilter;
    }

    @Bean
    public WallConfig wallConfig() {
        WallConfig config = new WallConfig();
        config.setMultiStatementAllow(true);
        config.setConditionDoubleConstAllow(true);
        config.setConditionAndAlwayTrueAllow(true);
        config.setSchemaCheck(false);
        return config;
    }


    @Bean
    public PaginationInterceptor paginationInterceptor() { //分页插件
        PaginationInterceptor page = new PaginationInterceptor();
        page.setDialectType("mysql"); //指定数据库类型
        return page;
    }
}
