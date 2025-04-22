package com.application.cloud.flow.task.controller;

import com.application.cloud.common.core.util.R;
import com.application.cloud.flow.task.entity.Process;
import com.application.cloud.flow.task.service.IProcessService;
import com.application.cloud.flow.task.vo.ProcessVO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/process")
public class ProcessController {
	
	private final IProcessService processService;
	
	/**
	 * 获取详细数据
	 *
	 * @param flowId
	 * @return
	 */
	@GetMapping("getDetail")
	public R<ProcessVO> getDetail(String flowId) {
		return processService.getDetail(flowId);
	}
	
	/**
	 * 创建流程
	 *
	 * @param process
	 * @return
	 */
	@PostMapping("create")
	public R create(@RequestBody Process process) {
		return processService.create(process);
	}
	
	/**
	 * 编辑表单
	 *
	 * @param flowId 摸板ID
	 * @param type   类型 stop using delete
	 * @return 操作结果
	 */
	@PutMapping("update/{flowId}")
	public R update(@PathVariable String flowId, @RequestParam String type,
	                @RequestParam(required = false) Long groupId) {
		return processService.update(flowId, type, groupId);
	}
	
}
