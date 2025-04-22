package com.application.cloud.admin.api.vo;

import com.application.cloud.admin.api.entity.SysMessageRelationEntity;
import lombok.Data;

/**
 * 消息阅读vo
 *
 * @author cloud
 * @date 2023/10/26
 */
@Data
public class SysMessageReadVO extends SysMessageRelationEntity {
	
	/**
	 * 文章标题
	 */
	private String title;
	
	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 姓名
	 */
	private String name;
	
}
