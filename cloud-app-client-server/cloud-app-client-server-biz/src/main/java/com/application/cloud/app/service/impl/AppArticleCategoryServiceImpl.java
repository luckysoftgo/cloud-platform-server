package com.application.cloud.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.application.cloud.app.api.entity.AppArticleCategoryEntity;
import com.application.cloud.app.mapper.AppArticleCategoryMapper;
import com.application.cloud.app.service.AppArticleCategoryService;
import org.springframework.stereotype.Service;

/**
 * 文章分类表
 *
 * @author pig
 * @date 2023-06-07 16:28:03
 */
@Service
public class AppArticleCategoryServiceImpl extends ServiceImpl<AppArticleCategoryMapper, AppArticleCategoryEntity>
		implements AppArticleCategoryService {
	
}
