package com.quartzHelloWorld;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class CronTriggerDemo {

	public static void main(String[] args) throws SchedulerException {
		//创建一个JobDetail实例，将该实例与HelloJob Class 绑定
		JobDetail jobDetail = (JobDetail) JobBuilder.newJob(HelloWorldJob.class).
				withIdentity("HelloJob", "group1").usingJobData("msg", "msg")
				.usingJobData("num", 3.14F).build();
		
		//SimpleTrigger例子 距离当前时间4秒钟后执行且每秒执行一次
		Date simpleDate = new Date();
		simpleDate.setTime(simpleDate.getTime()+4000L);
		CronTrigger cronTrigger = (CronTrigger)TriggerBuilder.newTrigger().withIdentity("myTrigger", "group1")
//				.startAt(simpleDate).
				.withSchedule(CronScheduleBuilder.cronSchedule("1 * * * * ? *")
						)
				.usingJobData("msg", "trigger")
				.usingJobData("num", 3.15D)
				.build();
		//创建Schedule实例
				SchedulerFactory sfact = new StdSchedulerFactory();
				Scheduler scheduler = sfact.getScheduler();
				scheduler.start();
				Date date = new Date();
				SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				//输出当前时间
				System.out.println("Current time is :"+sf.format(date));
				//调用任务计划执行Job
				scheduler.scheduleJob(jobDetail,cronTrigger);
				//scheduler 常用API
//				scheduler.scheduleJob(jobDetail, trigger)
//				Thread.sleep(2000L);
//				挂起、启动 true 等待所有正在执行的job执行完后，再关闭,false直接关闭 
//				scheduler.standby();
//				scheduler.start();
//				退出
//				scheduler.shutdown();
	}

}
