package com.application.cloud.common.core.config;

import com.application.cloud.common.core.factory.YamlPropertySourceFactory;
import org.springframework.context.annotation.PropertySource;

/**
 * 加载不可变配置项,这样的配置文件一舦是放在jar包中的，不会随着环境的变化而变化。
 *
 * @author cloud
 * @date 2024/7/20
 */
@PropertySource(value = "classpath:common-config.yml", factory = YamlPropertySourceFactory.class)
public class ImmutableConfiguration {
}
