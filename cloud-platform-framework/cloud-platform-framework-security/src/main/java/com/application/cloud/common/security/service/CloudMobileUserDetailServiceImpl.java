package com.application.cloud.common.security.service;

import com.application.cloud.admin.api.dto.UserInfo;
import com.application.cloud.admin.api.feign.RemoteUserService;
import com.application.cloud.common.core.constant.SecurityConstants;
import com.application.cloud.common.core.util.R;
import com.application.cloud.common.core.util.RetOps;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * @author aeizzz
 */
@Slf4j
@RequiredArgsConstructor
public class CloudMobileUserDetailServiceImpl implements CloudUserDetailsService {
	
	private final UserDetailsService CloudDefaultUserDetailsServiceImpl;
	
	private final RemoteUserService remoteUserService;
	
	@Override
	@SneakyThrows
	public UserDetails loadUserByUsername(String phone) {
		R<UserInfo> result = remoteUserService.social(phone);
		return getUserDetails(RetOps.of(result).getData());
	}
	
	@Override
	public UserDetails loadUserByUser(CloudUser cloudUser) {
		return CloudDefaultUserDetailsServiceImpl.loadUserByUsername(cloudUser.getUsername());
	}
	
	/**
	 * 支持所有的 mobile 类型
	 *
	 * @param clientId  目标客户端
	 * @param grantType 授权类型
	 * @return true/false
	 */
	@Override
	public boolean support(String clientId, String grantType) {
		return SecurityConstants.GRANT_MOBILE.equals(grantType);
	}
	
}
