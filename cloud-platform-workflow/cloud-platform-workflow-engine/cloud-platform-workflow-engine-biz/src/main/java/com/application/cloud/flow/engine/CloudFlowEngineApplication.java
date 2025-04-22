package com.application.cloud.flow.engine;

import com.application.cloud.common.feign.annotation.EnableCloudFeignClients;
import com.application.cloud.common.security.annotation.EnableCloudResourceServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cloud archetype
 * <p>
 * 项目启动类
 */
@EnableCloudFeignClients
@EnableDiscoveryClient
@EnableCloudResourceServer
@SpringBootApplication
public class CloudFlowEngineApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CloudFlowEngineApplication.class, args);
	}
	
}
