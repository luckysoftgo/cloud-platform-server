package com.application.cloud.app.controller;

import com.application.cloud.app.api.entity.AppPageEntity;
import com.application.cloud.app.service.AppIndexService;
import com.application.cloud.common.core.util.R;
import com.application.cloud.common.security.annotation.Inner;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * app 首页控制
 *
 * @author cloud
 * @date 2023/6/8
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/index")
@Tag(description = "App 页面控制", name = "app index")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AppIndexController {
	
	private final AppIndexService indexService;
	
	/**
	 * 首页
	 *
	 * @return Object
	 */
	@Inner(value = false)
	@GetMapping("/index")
	public R index() {
		Map<String, Object> detail = indexService.index();
		return R.ok(detail);
	}
	
	@Inner(value = false)
	@GetMapping("/config")
	public R config() {
		Map<String, Object> map = indexService.config();
		return R.ok(map);
	}
	
	@Inner(value = false)
	@GetMapping("/decorate")
	public R decorate(@Validated @RequestParam("id") Integer id) {
		AppPageEntity detail = indexService.decorate(id);
		return R.ok(detail);
	}
	
}
