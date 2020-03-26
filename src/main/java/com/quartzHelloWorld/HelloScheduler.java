package com.quartzHelloWorld;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class HelloScheduler {

	public static void main(String[] args) throws SchedulerException {
		//创建一个JobDetail实例，将该实例与HelloJob Class 绑定
		JobDetail jobDetail = (JobDetail) JobBuilder.newJob(HelloWorldJob.class).
				withIdentity("HelloJob", "group1").build();
		//创建一个Trigger实例，定义该job立即执行，并且每隔两秒种重复执行一次，直到永远。
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
				.startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000).
						repeatForever()).build();
		//创建Schedule实例
		SchedulerFactory sfact = new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println("Current time is :"+sf.format(date));
		scheduler.scheduleJob(jobDetail,trigger);
	}

}
