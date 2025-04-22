package com.application.cloud.common.feign.interceptor;

import feign.RequestInterceptor;
import org.springframework.http.HttpHeaders;

/**
 * @author cloud
 * @date 2024/3/15
 * <p>
 * http connection close
 */
public class CloudFeignRequestCloseInterceptor implements RequestInterceptor {
	
	/**
	 * set connection close
	 *
	 * @param template
	 */
	@Override
	public void apply(feign.RequestTemplate template) {
		template.header(HttpHeaders.CONNECTION, "close");
	}
	
}
