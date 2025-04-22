package com.application.cloud.app.controller;

import cn.hutool.core.collection.CollUtil;
import com.application.cloud.app.api.entity.AppTabbarEntity;
import com.application.cloud.app.service.AppTabbarService;
import com.application.cloud.common.core.util.R;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 底部导航
 *
 * @author pig
 * @date 2023-06-07 16:32:35
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/appTabbar")
@Tag(description = "appTabbar", name = "底部导航")
@SecurityRequirement(name = HttpHeaders.AUTHORIZATION)
public class AppTabbarController {
	
	private final AppTabbarService tabbarService;
	
	/**
	 * 查询导航列表
	 *
	 * @return R
	 */
	@Operation(summary = "查询导航列表", description = "查询导航列表")
	@GetMapping("/list")
	public R list() {
		return R.ok(tabbarService.list());
	}
	
	@Operation(summary = "更新导航", description = "更新导航")
	@PutMapping
	public R update(@RequestBody List<AppTabbarEntity> tabbarEntityList) {
		// 删除不在新增范围的导航菜单
		List<Long> idList = tabbarService.list().stream().map(AppTabbarEntity::getId).collect(Collectors.toList());
		List<Long> newIdList = tabbarEntityList.stream().map(AppTabbarEntity::getId).collect(Collectors.toList());
		
		// 计算需要删除的 ID 列表
		List<Long> idsToRemove = CollUtil.subtractToList(idList, newIdList);
		
		// 如果有需要删除的菜单，则批量删除
		if (CollUtil.isNotEmpty(idsToRemove)) {
			tabbarService.removeBatchByIds(idsToRemove);
		}
		
		return R.ok(tabbarService.saveOrUpdateBatch(tabbarEntityList));
	}
	
}
