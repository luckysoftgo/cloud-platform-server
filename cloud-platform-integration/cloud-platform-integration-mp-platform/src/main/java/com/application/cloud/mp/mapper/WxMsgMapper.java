package com.application.cloud.mp.mapper;

import com.application.cloud.common.data.datascope.CloudBaseMapper;
import com.application.cloud.mp.entity.WxMsg;
import org.apache.ibatis.annotations.Mapper;

/**
 * 微信消息
 *
 * @author JL
 * @date 2019-05-28 16:12:10
 */
@Mapper
public interface WxMsgMapper extends CloudBaseMapper<WxMsg> {

}
