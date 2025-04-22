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

package com.application.cloud.admin.controller;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.application.cloud.admin.api.entity.SysRouteConf;
import com.application.cloud.admin.service.SysRouteConfService;
import com.application.cloud.common.core.util.R;
import com.application.cloud.common.log.annotation.SysLog;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

/**
 * 路由
 *
 * @author cloud
 * @date 2018-11-06 10:17:18
 */
@RestController
@AllArgsConstructor
@RequestMapping("/route")
@Tag(description = "route", name = "动态路由管理模块")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class SysRouteConfController {
	
	private final SysRouteConfService sysRouteConfService;
	
	/**
	 * 获取当前定义的路由信息
	 *
	 * @return
	 */
	@GetMapping
	public R listRoutes(@RequestParam(required = false) String routeId) {
		return R.ok(sysRouteConfService.list(Wrappers.<SysRouteConf>lambdaQuery()
				.eq(StrUtil.isNotBlank(routeId), SysRouteConf::getRouteId, routeId)));
	}
	
	@DeleteMapping("/{routeId}")
	@PreAuthorize("@pms.hasPermission('sys_route_manage')")
	public R deleteRoute(@PathVariable String routeId) {
		return R
				.ok(sysRouteConfService.remove(Wrappers.<SysRouteConf>lambdaQuery().eq(SysRouteConf::getRouteId, routeId)));
	}
	
	/**
	 * 新增修改路由
	 *
	 * @param route 路由定义
	 * @return success
	 */
	@SysLog("新增修改路由")
	@PostMapping
	@PreAuthorize("@pms.hasPermission('sys_route_manage')")
	public R addOrUpdateRoute(@RequestBody JSONObject route) {
		return R.ok(sysRouteConfService.addOrUpdateRoute(route));
	}
	
	@SysLog("新增路由")
	@PostMapping("/save")
	@PreAuthorize("@pms.hasPermission('sys_route_manage')")
	public R saveRoute(@RequestBody SysRouteConf routeConf) {
		return R.ok(sysRouteConfService.saveRoute(routeConf));
	}
}
