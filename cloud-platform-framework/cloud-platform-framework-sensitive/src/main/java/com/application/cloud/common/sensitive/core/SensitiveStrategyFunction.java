package com.application.cloud.common.sensitive.core;

/**
 * @author cloud
 * @date 2024/6/27
 */
@FunctionalInterface
public interface SensitiveStrategyFunction<T, U, R> {
	
	R apply(T t, U u);
}
