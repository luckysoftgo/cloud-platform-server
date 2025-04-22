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

package com.application.cloud.app.handler;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.application.cloud.app.api.dto.AppUserInfo;
import com.application.cloud.app.api.entity.AppUser;
import com.application.cloud.app.service.AppUserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * @author cloud
 * @date 2018/11/18
 */
@Slf4j
@Component("APP-SMS")
@AllArgsConstructor
public class SmsLoginHandler extends AbstractLoginHandler {
	
	private final AppUserService appUserService;
	
	/**
	 * 验证码登录传入为手机号 不用不处理
	 *
	 * @param mobile
	 * @return
	 */
	@Override
	public String identify(String mobile) {
		return mobile;
	}
	
	/**
	 * 通过mobile 获取用户信息
	 *
	 * @param identify
	 * @return
	 */
	@Override
	public AppUserInfo info(String identify) {
		AppUser user = appUserService.getOne(Wrappers.<AppUser>query().lambda().eq(AppUser::getPhone, identify));
		
		// 未注册用户自动注册
		if (Objects.isNull(user)) {
			log.info("手机号未注册:{}", identify);
			AppUser appUser = new AppUser();
			appUser.setUsername(identify);
			appUser.setPhone(identify);
			appUser.setCreateBy(identify);
			appUser.setUpdateBy(identify);
			appUserService.save(appUser);
			AppUserInfo appUserDTO = new AppUserInfo();
			appUserDTO.setAppUser(appUser);
			return appUserDTO;
		}
		
		// 返回用户信息
		return appUserService.findUserInfo(user);
	}
	
	
	/**
	 * 绑定逻辑
	 *
	 * @param user     用户实体
	 * @param identify 渠道返回唯一标识
	 * @return
	 */
	@Override
	public Boolean bind(AppUser user, String identify) {
		user.setPhone(identify);
		appUserService.updateById(user);
		return true;
	}
	
}
