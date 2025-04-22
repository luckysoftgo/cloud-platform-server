package com.application.cloud.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.application.cloud.app.api.entity.AppPageEntity;
import com.application.cloud.app.mapper.AppPageMapper;
import com.application.cloud.app.service.AppPageService;
import org.springframework.stereotype.Service;

/**
 * 页面
 *
 * @author cloud
 * @date 2023-06-08 11:19:23
 */
@Service
public class AppPageServiceImpl extends ServiceImpl<AppPageMapper, AppPageEntity> implements AppPageService {

}
