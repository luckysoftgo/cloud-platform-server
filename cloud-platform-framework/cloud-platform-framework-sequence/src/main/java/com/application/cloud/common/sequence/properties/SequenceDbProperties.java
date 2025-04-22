package com.application.cloud.common.sequence.properties;

/**
 * @author cloud
 * @date 2019-05-26
 */

import lombok.Data;

/**
 * @author cloud
 * @date 2019/5/26
 * <p>
 * 发号器DB配置属性
 */
@Data
public class SequenceDbProperties {
	
	/**
	 * 表名称
	 */
	private String tableName = "cloud_sequence";
	
	/**
	 * 重试次数
	 */
	private int retryTimes = 1;
	
}
