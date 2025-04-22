package com.application.cloud.flow.engine.listeners;

import cn.hutool.core.lang.Dict;
import cn.hutool.core.util.StrUtil;
import cn.hutool.extra.spring.SpringUtil;
import com.application.cloud.common.core.util.R;
import com.application.cloud.flow.task.api.feign.RemoteFlowTaskService;
import com.application.cloud.flow.task.constant.ProcessInstanceConstant;
import com.application.cloud.flow.task.dto.Nobody;
import com.application.cloud.flow.task.dto.Node;
import com.application.cloud.flow.task.dto.NodeUser;
import com.application.cloud.flow.task.utils.NodeUtil;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.TaskService;
import org.flowable.task.service.delegate.DelegateTask;
import org.flowable.task.service.delegate.TaskListener;
import org.flowable.task.service.impl.persistence.entity.TaskEntityImpl;

/**
 * 审批创建监听器
 */
@Slf4j
public class ApprovalCreateListener implements TaskListener {
	
	/**
	 * 当任务被触发时，执行该方法
	 *
	 * @param delegateTask 委派的任务对象
	 */
	@Override
	public void notify(DelegateTask delegateTask) {
		log.debug(delegateTask.getClass().getCanonicalName());
		TaskService taskService = SpringUtil.getBean(TaskService.class);
		
		String assignee = delegateTask.getAssignee();
		String name = delegateTask.getName();
		log.debug("任务{}-执行人:{}", name, assignee);
		TaskEntityImpl taskEntity = (TaskEntityImpl) delegateTask;
		String nodeId = taskEntity.getTaskDefinitionKey();
		String processDefinitionId = taskEntity.getProcessDefinitionId();
		
		// 获取流程id
		String flowId = NodeUtil.getFlowId(processDefinitionId);
		
		if (StrUtil.isBlank(assignee)
				|| StrUtil.equals(ProcessInstanceConstant.DEFAULT_EMPTY_ASSIGN.toString(), assignee)) {
			
			RemoteFlowTaskService remoteFlowTaskService = SpringUtil.getBean(RemoteFlowTaskService.class);
			
			// 查询节点原始数据
			Node node = remoteFlowTaskService.queryNodeOriData(flowId, nodeId).getData();
			
			Nobody nobody = node.getNobody();
			
			String handler = nobody.getHandler();
			
			if (StrUtil.equals(handler, ProcessInstanceConstant.USER_TASK_NOBODY_HANDLER_TO_PASS)) {
				// 直接通过
				Dict param = Dict.create().set(StrUtil.format("{}_approve_condition", nodeId), true);
				taskService.complete(taskEntity.getId(), param);
			}
			
			if (StrUtil.equals(handler, ProcessInstanceConstant.USER_TASK_NOBODY_HANDLER_TO_ADMIN)) {
				// 指派给管理员
				
				R<Long> longR = remoteFlowTaskService.queryProcessAdmin(flowId);
				
				Long adminId = longR.getData();
				
				taskService.setAssignee(taskEntity.getId(), String.valueOf(adminId));
			}
			
			if (StrUtil.equals(handler, ProcessInstanceConstant.USER_TASK_NOBODY_HANDLER_TO_USER)) {
				// 指定用户
				
				NodeUser nodeUser = nobody.getAssignedUser().get(0);
				
				taskService.setAssignee(taskEntity.getId(), nodeUser.getId().toString());
			}
			
			if (StrUtil.equals(handler, ProcessInstanceConstant.USER_TASK_NOBODY_HANDLER_TO_REFUSE)) {
				// 结束
				Dict param = Dict.create().set(StrUtil.format("{}_approve_condition", nodeId), false);
				taskService.complete(taskEntity.getId(), param);
				
			}
			
		}
		
	}
	
}
