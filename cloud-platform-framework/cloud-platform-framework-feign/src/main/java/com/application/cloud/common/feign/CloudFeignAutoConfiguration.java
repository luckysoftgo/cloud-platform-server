/*
 *    Copyright (c) 2018-2025, Cloud All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 * this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above copyright
 * notice, this list of conditions and the following disclaimer in the
 * documentation and/or other materials provided with the distribution.
 * Neither the name of the pig4cloud.com developer nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 * Author: cloud
 */

package com.application.cloud.common.feign;

import com.application.cloud.common.feign.endpoint.FeignClientEndpoint;
import com.application.cloud.common.feign.interceptor.CloudFeignInnerRequestInterceptor;
import com.application.cloud.common.feign.interceptor.CloudFeignRequestCloseInterceptor;
import feign.Feign;
import org.springframework.boot.actuate.autoconfigure.endpoint.condition.ConditionalOnAvailableEndpoint;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.CloudFeignClientsRegistrar;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author cloud
 * @date 2020/2/8
 * <p>
 * feign 自动化配置
 */
@Configuration
@ConditionalOnClass(Feign.class)
@Import(CloudFeignClientsRegistrar.class)
@AutoConfigureAfter(EnableFeignClients.class)
public class CloudFeignAutoConfiguration {
	
	/**
	 * feign actuator endpoint
	 *
	 * @param context
	 * @return FeignClientEndpoint
	 */
	@Bean
	@ConditionalOnMissingBean
	@ConditionalOnAvailableEndpoint
	public FeignClientEndpoint feignClientEndpoint(ApplicationContext context) {
		return new FeignClientEndpoint(context);
	}
	
	/**
	 * add http connection close header
	 *
	 * @return CloudFeignRequestCloseInterceptor
	 */
	@Bean
	public CloudFeignRequestCloseInterceptor pigFeignRequestCloseInterceptor() {
		return new CloudFeignRequestCloseInterceptor();
	}
	
	/**
	 * add inner request header
	 *
	 * @return CloudFeignInnerRequestInterceptor
	 */
	@Bean
	public CloudFeignInnerRequestInterceptor pigFeignInnerRequestInterceptor() {
		return new CloudFeignInnerRequestInterceptor();
	}
}
