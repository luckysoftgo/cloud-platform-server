package com.application.cloud.admin.api.feign;

import com.application.cloud.common.core.constant.ServiceNameConstants;
import com.application.cloud.common.core.util.R;
import com.application.cloud.common.feign.annotation.NoToken;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * @author cloud
 * @date 2020/5/12
 * <p>
 * 查询参数相关
 */
@FeignClient(contextId = "remoteParamService", value = ServiceNameConstants.UPMS_SERVICE)
public interface RemoteParamService {
	
	/**
	 * 通过key 查询参数配置
	 *
	 * @param key key
	 * @return
	 */
	@NoToken
	@GetMapping("/param/publicValue/{key}")
	R<String> getByKey(@PathVariable("key") String key);
	
	/**
	 * 通过keys 查询参数配置
	 *
	 * @param keys keys
	 * @return map
	 */
	@GetMapping("/param/publicValues")
	R<Map<String, Object>> getByKeys(@RequestParam("keys") String[] keys);
	
}
