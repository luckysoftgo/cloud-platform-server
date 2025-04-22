/*
 *
 *      Copyright (c) 2018-2025, Cloud All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: cloud
 *
 */

package com.application.cloud.common.log;

import com.application.cloud.admin.api.feign.RemoteLogService;
import com.application.cloud.common.core.util.KeyStrResolver;
import com.application.cloud.common.log.aspect.SysLogAspect;
import com.application.cloud.common.log.config.CloudLogProperties;
import com.application.cloud.common.log.event.SysLogListener;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author cloud
 * @date 2018/6/28
 * <p>
 * 日志自动配置
 */
@EnableAsync
@Configuration(proxyBeanMethods = false)
@EnableConfigurationProperties(CloudLogProperties.class)
@ConditionalOnProperty(value = "cloud.log.enabled", matchIfMissing = true)
public class LogAutoConfiguration {
	
	@Bean
	public SysLogListener sysLogListener(CloudLogProperties logProperties, RemoteLogService remoteLogService) {
		return new SysLogListener(remoteLogService, logProperties);
	}
	
	@Bean
	public SysLogAspect sysLogAspect(KeyStrResolver resolver) {
		return new SysLogAspect(resolver);
	}
	
}
