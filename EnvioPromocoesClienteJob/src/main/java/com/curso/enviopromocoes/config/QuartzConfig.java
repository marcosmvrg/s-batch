package com.curso.enviopromocoes.config;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.curso.enviopromocoes.job.EnvioPromocoesClientesScheduleJob;

@Configuration
public class QuartzConfig {
	
	@Bean
	public JobDetail quartzJobDetail() {
		return JobBuilder.newJob(EnvioPromocoesClientesScheduleJob.class)
				.storeDurably()
				.build();
	}
	
	@Bean 
	public Trigger jobTrigger() {
		SimpleScheduleBuilder schedule = SimpleScheduleBuilder
				.simpleSchedule()
				.withIntervalInSeconds(60)
				.withRepeatCount(2);
			
		return TriggerBuilder.newTrigger().forJob(quartzJobDetail())
				.withSchedule(schedule)
				.build();
	}
	
	
}
