package com.application.cloud.admin.api.dto;

import lombok.Data;

/**
 * 文件传输DTO
 *
 * @author cloud
 * @date 2023/7/26
 */
@Data
public class SysFileGroupDTO {
	
	/**
	 * 文件组
	 */
	private Long groupId;
	
	/**
	 * 文件id
	 */
	private Long[] ids;
	
}
