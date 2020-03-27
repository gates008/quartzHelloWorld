package com.quartzHelloWorld;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class SimpleTriggerDemo {

	public static void main(String[] args) throws SchedulerException {
		//创建一个JobDetail实例，将该实例与HelloJob Class 绑定
		JobDetail jobDetail = (JobDetail) JobBuilder.newJob(HelloWorldJob.class).
				withIdentity("HelloJob", "group1").usingJobData("msg", "msg")
				.usingJobData("num", 3.14F).build();
		
		//SimpleTrigger例子 距离当前时间4秒钟后执行且执行一次任务
		Date simpleDate = new Date();
		simpleDate.setTime(simpleDate.getTime()+4000L);
		SimpleTrigger simpleTrigger = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
				.startAt(simpleDate)
				.usingJobData("msg", "trigger")
				.usingJobData("num", 3.15D)
				.startNow().build();
		//SimpleTrigger例子 距离当前时间4秒钟后执行，且每隔2秒后执行一次,不断执行
		SimpleTrigger simpleTrigger1 = (SimpleTrigger)TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
				.startAt(simpleDate).
				withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)
						.withRepeatCount(SimpleTrigger.REPEAT_INDEFINITELY))
				.usingJobData("msg", "trigger")
				.usingJobData("num", 3.15D)
				.startNow().build();
		//创建Schedule实例
				SchedulerFactory sfact = new StdSchedulerFactory();
				Scheduler scheduler = sfact.getScheduler();
				scheduler.start();
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//输出当前时间
				System.out.println("Current time is :"+sf.format(date));
				//调用任务计划执行Job
				scheduler.scheduleJob(jobDetail,simpleTrigger1);
	}

}
