package com.application.cloud.admin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.application.cloud.admin.api.entity.SysSystemConfigEntity;
import com.application.cloud.common.core.util.R;

/**
 * SYS 系统配置服务
 *
 * @author cloud
 * @date 2024/07/18
 */
public interface SysSystemConfigService extends IService<SysSystemConfigEntity> {
	
	/**
	 * 列出系统配置
	 *
	 * @param query 查询
	 * @return {@link R }
	 */
	R listSystemConfig(SysSystemConfigEntity query);
	
	/**
	 * 系统配置
	 *
	 * @param page            页
	 * @param sysSystemConfig sys 系统配置
	 * @return {@link R }
	 */
	R pageSystemConfig(Page page, SysSystemConfigEntity sysSystemConfig);
	
	/**
	 * 更新系统配置
	 *
	 * @param sysSystemConfig sys 系统配置
	 * @return {@link R }
	 */
	R updateSystemConfig(SysSystemConfigEntity sysSystemConfig);
}
