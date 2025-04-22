package com.application.cloud.common.sensitive.util;

import com.application.cloud.common.core.constant.ServiceNameConstants;
import com.application.cloud.common.core.util.R;
import com.application.cloud.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

/**
 * @author cloud
 * @date 2024/7/7
 */
@FeignClient(contextId = "remoteSensitiveService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteSensitiveService {
	
	@NoToken
	@GetMapping("/sysSensitiveWord/remote/list/{type}")
	R<List<String>> list(@PathVariable String type);
}
