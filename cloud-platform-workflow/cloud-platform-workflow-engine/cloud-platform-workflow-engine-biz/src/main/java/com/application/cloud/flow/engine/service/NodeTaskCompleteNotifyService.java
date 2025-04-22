package com.application.cloud.flow.engine.service;

import cn.hutool.core.lang.Validator;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONUtil;
import com.application.cloud.common.core.util.RetOps;
import com.application.cloud.common.security.util.NonWebTokenContextHolder;
import com.application.cloud.flow.task.api.feign.RemoteFlowTaskService;
import com.application.cloud.flow.task.dto.ProcessNodeRecordAssignUserParamDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * 节点任务完成通知处理
 *
 * @author cloud
 * @date 2024/7/9
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class NodeTaskCompleteNotifyService {
	
	private final RemoteFlowTaskService remoteFlowTaskService;
	
	@Async
	public void sendNotify(String token, ProcessNodeRecordAssignUserParamDto taskCompleteParamDto) {
		NonWebTokenContextHolder.setToken(token);
		RetOps.of(remoteFlowTaskService.queryNodeOriData(taskCompleteParamDto.getFlowId(), taskCompleteParamDto.getNodeId()))
				.getData()
				.ifPresent(node -> {
					if (Validator.isUrl(node.getEventConfig())) {
						HttpResponse response = HttpRequest.post(node.getEventConfig())
								.body(JSONUtil.toJsonStr(taskCompleteParamDto))
								.timeout(10000)
								.execute();
						log.debug("节点任务完成通知处理完成:{}，数据：{}", response.getStatus(), taskCompleteParamDto);
					}
				});
	}
}
