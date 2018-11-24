package com.weikun.server.config;

import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 创建者：weikun【YST】   日期：2018/7/19
 * 说说功能：
 */
@Configuration
@AutoConfigureAfter(MyBatisConfig.class)
public class MyBatisMapperScannerConfig {
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer(){
        MapperScannerConfigurer mc=new MapperScannerConfigurer();
        mc.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mc.setBasePackage("com.weikun.server.mapper");

        return mc;
    }
}
