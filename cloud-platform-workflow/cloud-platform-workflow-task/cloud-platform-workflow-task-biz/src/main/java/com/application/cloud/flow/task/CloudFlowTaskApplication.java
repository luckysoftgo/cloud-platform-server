package com.application.cloud.flow.task;

import com.application.cloud.common.feign.annotation.EnableCloudFeignClients;
import com.application.cloud.common.security.annotation.EnableCloudResourceServer;
import com.application.cloud.common.swagger.annotation.EnableOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author cloud archetype
 * <p>
 * 项目启动类
 */
@EnableOpenApi("task")
@EnableCloudFeignClients
@EnableDiscoveryClient
@EnableCloudResourceServer
@SpringBootApplication
public class CloudFlowTaskApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CloudFlowTaskApplication.class, args);
	}
	
}


