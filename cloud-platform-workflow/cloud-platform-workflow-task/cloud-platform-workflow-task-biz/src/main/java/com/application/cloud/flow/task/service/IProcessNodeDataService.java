package com.application.cloud.flow.task.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.application.cloud.common.core.util.R;
import com.application.cloud.flow.task.dto.Node;
import com.application.cloud.flow.task.dto.ProcessNodeDataDto;
import com.application.cloud.flow.task.entity.ProcessNodeData;

/**
 * <p>
 * 流程节点数据 服务类
 * </p>
 *
 * @author Vincent
 * @since 2023-05-07
 */
public interface IProcessNodeDataService extends IService<ProcessNodeData> {
	
	/**
	 * 保存流程节点数据
	 *
	 * @param processNodeDataDto
	 * @return
	 */
	R saveNodeData(ProcessNodeDataDto processNodeDataDto);
	
	/***
	 * 获取节点数据
	 * @param flowId
	 * @param nodeId
	 * @return
	 */
	R<Node> getNodeData(String flowId, String nodeId);
	
}
