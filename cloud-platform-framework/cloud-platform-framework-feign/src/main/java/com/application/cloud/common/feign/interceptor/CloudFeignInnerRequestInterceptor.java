package com.application.cloud.common.feign.interceptor;

import com.application.cloud.common.core.constant.SecurityConstants;
import com.application.cloud.common.feign.annotation.NoToken;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import org.springframework.core.Ordered;

import java.lang.reflect.Method;

/**
 * @author cloud
 * @date 2024/6/1
 */
public class CloudFeignInnerRequestInterceptor implements RequestInterceptor, Ordered {
	
	/**
	 * Called for every request. Add data using methods on the supplied
	 * {@link RequestTemplate}.
	 *
	 * @param template
	 */
	@Override
	public void apply(RequestTemplate template) {
		Method method = template.methodMetadata().method();
		NoToken noToken = method.getAnnotation(NoToken.class);
		if (noToken != null) {
			template.header(SecurityConstants.FROM, SecurityConstants.FROM_IN);
		}
	}
	
	@Override
	public int getOrder() {
		return Integer.MIN_VALUE;
	}
	
}
