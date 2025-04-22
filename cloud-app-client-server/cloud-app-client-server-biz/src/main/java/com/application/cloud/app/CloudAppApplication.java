package com.application.cloud.app;

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
@EnableOpenApi("app")
@EnableCloudFeignClients
@EnableDiscoveryClient
@EnableCloudResourceServer
@SpringBootApplication
public class CloudAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CloudAppApplication.class, args);
	}
	
}
