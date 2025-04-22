package com.application.cloud.daemon.quartz;

import com.application.cloud.common.feign.annotation.EnableCloudFeignClients;
import com.application.cloud.common.security.annotation.EnableCloudResourceServer;
import com.application.cloud.common.swagger.annotation.EnableOpenApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * @author frwcloud
 * @date 2019/01/23 定时任务模块
 */
@EnableOpenApi("job")
@EnableCloudFeignClients
@EnableCloudResourceServer
@EnableDiscoveryClient
@SpringBootApplication
public class CloudDaemonQuartzApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CloudDaemonQuartzApplication.class, args);
	}
	
}
