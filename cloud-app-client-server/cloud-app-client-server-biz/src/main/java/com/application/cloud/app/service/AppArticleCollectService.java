package com.application.cloud.app.service;

import com.github.yulichang.base.MPJBaseService;
import com.application.cloud.app.api.entity.AppArticleCollectEntity;

public interface AppArticleCollectService extends MPJBaseService<AppArticleCollectEntity> {
	
	Boolean saveArticleCollect(AppArticleCollectEntity appArticleCollect);
	
}
