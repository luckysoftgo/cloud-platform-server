package com.application.cloud.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.application.cloud.common.core.util.R;
import com.application.cloud.mp.entity.WxMsg;

/**
 * 微信消息
 *
 * @author JL
 * @date 2019-05-28 16:12:10
 */
public interface WxMsgService extends IService<WxMsg> {
	
	/**
	 * 保存信息并向用户推送
	 *
	 * @param wxMsg
	 * @return
	 */
	R saveAndPushMsg(WxMsg wxMsg);
	
}
