package com.application.cloud.common.api.encrypt;

import cn.hutool.crypto.SecureUtil;
import com.application.cloud.common.api.encrypt.config.ApiEncryptParamConfiguration;
import com.application.cloud.common.api.encrypt.config.ApiEncryptProperties;
import com.application.cloud.common.api.encrypt.core.ApiDecryptRequestBodyAdvice;
import com.application.cloud.common.api.encrypt.core.ApiEncryptResponseBodyAdvice;
import com.application.cloud.common.api.encrypt.core.DefaultSecretKeyResolver;
import com.application.cloud.common.api.encrypt.core.ISecretKeyResolver;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * api 配置加载类
 *
 * @author cloud
 * @date 2022/10/14
 */
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(ApiEncryptProperties.class)
@Import({ApiEncryptParamConfiguration.class, ApiDecryptRequestBodyAdvice.class, ApiEncryptResponseBodyAdvice.class})
@ConditionalOnProperty(value = ApiEncryptProperties.PREFIX + ".enable", havingValue = "true", matchIfMissing = true)
public class ApiEncryptAutoConfiguration {
	
	static {
		// 关闭hutool 强制关闭Bouncy Castle库的依赖
		SecureUtil.disableBouncyCastle();
	}
	
	/**
	 * 默认的 key 获取策略
	 *
	 * @param apiEncryptProperties
	 * @return
	 */
	@Bean
	@ConditionalOnMissingBean
	public ISecretKeyResolver secretKeyResolver(ApiEncryptProperties apiEncryptProperties) {
		return new DefaultSecretKeyResolver(apiEncryptProperties);
	}
	
}
