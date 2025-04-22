package com.application.cloud.common.audit.handle;

import com.application.cloud.admin.api.entity.SysAuditLog;
import com.application.cloud.common.audit.annotation.Audit;
import org.javers.core.Changes;

import java.util.List;

/**
 * @author cloud
 * @date 2023/2/26
 * <p>
 * 审计日志处理器
 */
public interface IAuditLogHandle {
	
	void handle(Audit audit, Changes changes);
	
	void asyncSend(List<SysAuditLog> auditLogList);
	
}
