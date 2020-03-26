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
		Date startDate = new Date();
		startDate.setTime(startDate.getTime()+3000);
		Date endDate = new Date();
		endDate.setTime(endDate.getTime()+6000);
		
		//创建一个JobDetail实例，将该实例与HelloJob Class 绑定
		JobDetail jobDetail = (JobDetail) JobBuilder.newJob(HelloWorldJob.class).
				withIdentity("HelloJob", "group1").usingJobData("msg", "msg")
				.usingJobData("num", 3.14F).build();
		//创建一个Trigger实例，定义该job立即执行，并且每隔两秒种重复执行一次，直到永远。
		Trigger trigger = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
				.usingJobData("msg", "trigger")
				.usingJobData("num", 3.15D)
				.startNow().withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000).
						repeatForever()).build();
		//设置开始结束时间
//		Trigger trigger1 = TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
//				.startAt(startDate).endAt(endDate)
//				.usingJobData("msg", "trigger")
//				.usingJobData("num", 3.15D)
//				.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInMilliseconds(1000).
//						repeatForever()).build();
		//创建Schedule实例
		SchedulerFactory sfact = new StdSchedulerFactory();
		Scheduler scheduler = sfact.getScheduler();
		scheduler.start();
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//输出当前时间
		System.out.println("Current time is :"+sf.format(date));
		//调用任务计划执行Job
		scheduler.scheduleJob(jobDetail,trigger);
	}

}
