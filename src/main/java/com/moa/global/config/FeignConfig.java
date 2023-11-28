package com.moa.global.config;


import feign.Logger;
import org.springframework.context.annotation.Bean;


public class FeignConfig {

	@Bean
	Logger.Level feignLoggerLevel() {
		return Logger.Level.FULL;
	}

	//
	//	@Bean
	//	public RequestInterceptor basicFeignRequestInterceptor() {
	//		return new ColonInterceptor();
	//	}
	//
	//
	//	public static class ColonInterceptor implements RequestInterceptor {
	//
	//		@Override
	//		public void apply(RequestTemplate template) {
	//			template.uri(template.path().replaceAll("%3A", ":"));
	//		}
	//
	//	}

}