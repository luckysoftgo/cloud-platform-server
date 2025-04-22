package com.application.cloud.common.sensitive.core;

import com.application.cloud.common.sensitive.annotation.Sensitive;

/**
 * 脱敏逻辑增强处理
 *
 * @author cloud
 * @date 2024/6/27
 */
public interface SensitiveService {
	/**
	 * 是否脱敏
	 */
	boolean isSensitive(Sensitive sensitive);
}
