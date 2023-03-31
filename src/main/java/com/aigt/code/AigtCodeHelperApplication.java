package com.aigt.code;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;


/**
 * 启动程序
 */
@Slf4j
@MapperScan(value = {"com.aigt.code.dao"})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableTransactionManagement
public class AigtCodeHelperApplication {
    public static void main(String[] args) {
        SpringApplication.run(AigtCodeHelperApplication.class, args);
        System.out.println("start success!");
    }
}
