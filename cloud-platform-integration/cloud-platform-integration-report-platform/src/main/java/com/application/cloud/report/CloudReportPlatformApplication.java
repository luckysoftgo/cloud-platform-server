package com.application.cloud.report;

import com.application.cloud.common.feign.annotation.EnableCloudFeignClients;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 业务模板
 *
 * @author lr
 * @since 2023-04-05
 */
@EnableCloudFeignClients
@SpringBootApplication
public class CloudReportPlatformApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CloudReportPlatformApplication.class);
	}
	
}
