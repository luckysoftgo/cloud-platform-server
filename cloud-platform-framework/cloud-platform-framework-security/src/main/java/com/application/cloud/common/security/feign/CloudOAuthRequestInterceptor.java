package com.application.cloud.common.security.feign;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.application.cloud.common.core.constant.SecurityConstants;
import com.application.cloud.common.core.util.WebUtils;
import com.application.cloud.common.security.util.NonWebTokenContextHolder;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.security.oauth2.server.resource.web.BearerTokenResolver;

import javax.servlet.http.HttpServletRequest;

import java.util.Collection;

/**
 * oauth2 feign token传递
 * <p>
 * 重新 OAuth2FeignRequestInterceptor ，官方实现部分常见不适用
 *
 * @author cloud
 * @date 2022/5/29
 */
@Slf4j
@RequiredArgsConstructor
public class CloudOAuthRequestInterceptor implements RequestInterceptor {
	
	private final BearerTokenResolver tokenResolver;
	
	/**
	 * Create a template with the header of provided name and extracted extract </br>
	 * <p>
	 * 1. 如果使用 非web 请求，header 区别 </br>
	 * <p>
	 * 2. 根据authentication 还原请求token
	 *
	 * @param template
	 */
	@Override
	public void apply(RequestTemplate template) {
		Collection<String> fromHeader = template.headers().get(SecurityConstants.FROM);
		// 带from 请求直接跳过
		if (CollUtil.isNotEmpty(fromHeader) && fromHeader.contains(SecurityConstants.FROM_IN)) {
			return;
		}
		
		// 非web 请求直接跳过
		String token = "";
		if (WebUtils.getRequest() == null) {
			token = NonWebTokenContextHolder.getToken();
		} else {
			HttpServletRequest request = WebUtils.getRequest();
			// 避免请求参数的 query token 无法传递
			token = tokenResolver.resolve(request);
		}
		
		if (StrUtil.isBlank(token)) {
			return;
		}
		template.header(HttpHeaders.AUTHORIZATION,
				String.format("%s %s", OAuth2AccessToken.TokenType.BEARER.getValue(), token));
		
	}
	
}
