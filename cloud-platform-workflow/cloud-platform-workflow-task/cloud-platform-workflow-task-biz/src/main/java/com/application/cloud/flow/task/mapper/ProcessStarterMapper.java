package com.application.cloud.flow.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.application.cloud.flow.task.entity.ProcessStarter;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 流程发起人 Mapper 接口
 * </p>
 *
 * @author Vincent
 * @since 2023-05-30
 */
@Mapper
public interface ProcessStarterMapper extends BaseMapper<ProcessStarter> {

}
