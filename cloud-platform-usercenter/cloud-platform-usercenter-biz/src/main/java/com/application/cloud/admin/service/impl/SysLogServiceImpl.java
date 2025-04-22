/*
 *
 *      Copyright (c) 2018-2025, Cloud All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the pig4cloud.com developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: cloud
 *
 */

package com.application.cloud.admin.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ArrayUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.application.cloud.admin.api.dto.SysLogDTO;
import com.application.cloud.admin.api.entity.SysLog;
import com.application.cloud.admin.api.vo.PreLogVO;
import com.application.cloud.admin.mapper.SysLogMapper;
import com.application.cloud.admin.service.SysLogService;
import com.application.cloud.common.data.tenant.TenantBroker;
import com.application.cloud.common.data.tenant.TenantContextHolder;
import com.application.cloud.common.log.util.LogTypeEnum;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 日志表 服务实现类
 * </p>
 *
 * @author cloud
 * @since 2017-11-20
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
	
	/**
	 * 批量插入前端错误日志
	 *
	 * @param preLogVoList 日志信息
	 * @return true/false
	 */
	@Override
	public Boolean saveBatchLogs(List<PreLogVO> preLogVoList) {
		List<SysLog> sysLogs = preLogVoList.stream().map(pre -> {
			SysLog log = new SysLog();
			log.setLogType(LogTypeEnum.ERROR.getType());
			log.setTitle(pre.getInfo());
			log.setException(pre.getStack());
			log.setParams(pre.getMessage());
			log.setCreateTime(LocalDateTime.now());
			log.setRequestUri(pre.getUrl());
			log.setCreateBy(pre.getUser());
			return log;
		}).collect(Collectors.toList());
		return this.saveBatch(sysLogs);
	}
	
	@Override
	public Page getLogByPage(Page page, SysLogDTO sysLog) {
		
		LambdaQueryWrapper<SysLog> wrapper = Wrappers.lambdaQuery();
		if (StrUtil.isNotBlank(sysLog.getLogType())) {
			wrapper.eq(SysLog::getLogType, sysLog.getLogType());
		}
		
		if (ArrayUtil.isNotEmpty(sysLog.getCreateTime())) {
			wrapper.ge(SysLog::getCreateTime, sysLog.getCreateTime()[0])
					.le(SysLog::getCreateTime, sysLog.getCreateTime()[1]);
		}
		
		wrapper.eq(StrUtil.isNotBlank(sysLog.getServiceId()), SysLog::getServiceId, sysLog.getServiceId());
		return baseMapper.selectPage(page, wrapper);
	}
	
	/**
	 * 插入日志
	 *
	 * @param sysLog 日志对象
	 * @return true/false
	 */
	@Override
	@Transactional(rollbackFor = Exception.class)
	public Boolean saveLog(SysLogDTO sysLog) {
		TenantBroker.applyAs(sysLog::getTenantId, tenantId -> {
			TenantContextHolder.setTenantId(tenantId);
			SysLog log = new SysLog();
			BeanUtils.copyProperties(sysLog, log, "createTime");
			return baseMapper.insert(log);
		});
		return Boolean.TRUE;
	}
	
	/**
	 * sum 函数计算三十天内的数据
	 *
	 * @return list map
	 */
	@Override
	public List<Map<String, Object>> getLogSum() {
		// 查询createTime最近30天的数据
		List<SysLog> sysLogList = baseMapper
				.selectList(Wrappers.<SysLog>lambdaQuery().ge(SysLog::getCreateTime, LocalDateTime.now().minusDays(30)));
		
		return sysLogList.stream()
				// 按照日期进行分组
				.collect(Collectors.groupingBy(log -> DateUtil.format(log.getCreateTime(), DatePattern.NORM_DATE_PATTERN)))
				.entrySet().stream()
				// 将每个日期对应的日志按日志类型分组，并计算每种类型的日志数量
				.map(entry -> {
					Map<String, Object> map = entry.getValue().stream()
							.collect(Collectors.groupingBy(SysLog::getLogType,
									Collectors.collectingAndThen(Collectors.counting(), Long::intValue)));
					map.put(SysLog.Fields.createTime, entry.getKey());
					return map;
				})
				// 按createTime升序排序
				.sorted(Comparator.comparing(map -> map.get(SysLog.Fields.createTime).toString()))
				.collect(Collectors.toList());
	}
	
}
