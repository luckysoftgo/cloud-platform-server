package com.application.cloud.common.datasource.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

/**
 * @author cloud
 * @date 2022/8/8
 * <p>
 * 注入SQL 格式化的插件
 */
@ConditionalOnClass(name = "com.application.cloud.common.data.mybatis.DruidSqlLogFilter")
public class DynamicLogConfiguration {

}
