/*
 * Copyright 1999-2018 Alibaba Group Holding Ltd.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.alibaba.nacos;

import com.alibaba.nacos.console.config.ConfigConstants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * @author nacos
 * <p>
 * nacos console 源码运行，方便开发 生产从官网下载zip最新版集群配置运行
 */
@Slf4j
@EnableScheduling
@SpringBootApplication
public class CloudNacosApplication {
	
	public static void main(String[] args) {
		if (initEnv()) {
			log.info("=============== nacos console 开始启动... ===================");
			SpringApplication.run(CloudNacosApplication.class, args);
			log.info("=============== nacos console 启动成功！！！===================");
		}
	}
	
	/**
	 * 初始化运行环境
	 */
	private static boolean initEnv() {
		// 初始化环境变量...
		System.setProperty(ConfigConstants.STANDALONE_MODE, "true");
		System.setProperty(ConfigConstants.AUTH_ENABLED, "true");
		System.setProperty(ConfigConstants.LOG_BASEDIR, "/mnt/applogs/nacos/");
		System.setProperty(ConfigConstants.LOG_ENABLED, "false");
		System.setProperty(ConfigConstants.NACOS_CONTEXT_PATH, "/nacos");
		
		// 模式，日志路径，缓存目录等...
		System.setProperty("server.tomcat.basedir", "/mnt/applogs/nacos/");
		System.setProperty("JM.LOG.PATH", "/mnt/applogs/nacos/");
		System.setProperty("com.alibaba.nacos.naming.cache.dir", "/mnt/applogs/nacos/naming/");
		System.setProperty("JM.SNAPSHOT.PATH", "/mnt/applogs/nacos/config/");
		System.setProperty("com.alibaba.nacos.naming.log.level", "error");
		System.setProperty("com.alibaba.nacos.config.log.level", "warn");
		
		return true;
	}
	
}
