package com.application.cloud.flow.task.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.application.cloud.flow.task.entity.ProcessCopy;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * 流程抄送数据 Mapper 接口
 * </p>
 *
 * @author Vincent
 * @since 2023-05-20
 */
@Mapper
public interface ProcessCopyMapper extends BaseMapper<ProcessCopy> {

}
