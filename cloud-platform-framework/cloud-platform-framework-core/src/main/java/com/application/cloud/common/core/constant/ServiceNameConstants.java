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

package com.application.cloud.common.core.constant;

/**
 * @author cloud
 * @date 2018年06月22日16:41:01 服务名称
 */
public interface ServiceNameConstants {
	
	/**
	 * 认证中心
	 */
	String AUTH_SERVICE = "cloud-platform-authclient";
	
	/**
	 * UMPS模块
	 */
	String UPMS_SERVICE = "cloud-platform-userclient";
	
	/**
	 * app服务
	 */
	String APP_SERVER = "cloud-app-client-server-biz";
	
	/**
	 * 流程引擎
	 */
	String FLOW_ENGINE_SERVER = "cloud-platform-workflow-engine-biz";
	
	/**
	 * 流程工单
	 */
	String FLOW_TASK_SERVER = "cloud-platform-workflow-task-biz";
	
	/**
	 * 代码生成模块
	 */
	String CODEGEN_SERVICE = "cloud-platform-integration-codegen";
	
}
