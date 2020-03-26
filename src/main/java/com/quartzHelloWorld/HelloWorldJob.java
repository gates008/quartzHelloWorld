package com.quartzHelloWorld;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobKey;
import org.quartz.TriggerKey;

public class HelloWorldJob implements Job{
	
	private String msg;
	private Double num;
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		//HellWorld 执行
		Date date = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(sf.format(date)+"----hello Test---");
		
		//withIdentity获取方式
		JobKey key = context.getJobDetail().getKey();
		System.out.println(sf.format(date)+"---"+key.getName()+"---"+key.getGroup());
		TriggerKey tkey = context.getTrigger().getKey();
		System.out.println(sf.format(date)+"---"+tkey.getName()+"---"+tkey.getGroup());
		//JobDataMap 获取方式一
		JobDataMap job_dataMap = context.getJobDetail().getJobDataMap();
		JobDataMap trigger_DataMap1 = context.getTrigger().getJobDataMap();
		String jobmsg = job_dataMap.getString("msg");
        Float jobnum = job_dataMap.getFloat("num");
        String tribmsg = trigger_DataMap1.getString("msg");
        Double trinum = trigger_DataMap1.getDouble("num");
        System.out.println("JobDataMap: "+jobmsg +" " +jobnum);
        System.out.println("TriDataMap: "+tribmsg +" " +trinum);
        //JobDataMap 获取方式二 如果key相同以trigger为主
        JobDataMap job_dataMap1 = context.getMergedJobDataMap();
        String tribmsg1 = job_dataMap1.getString("msg");
        Double trinum1 = job_dataMap1.getDouble("num");
        System.out.println("TriDataMap1: "+tribmsg1 +" " +trinum1);
        //JobDataMap 获取方式三 get/set方式  如果key相同以trigger为主
        System.out.println("TriDataMap2: "+msg +" " +num);
        //设置开始结束时间打印
//        System.out.println("开始时间："+context.getTrigger().getStartTime() +
//        		"结束时间：" +context.getTrigger().getEndTime());
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public void setNum(Double num) {
		this.num = num;
	}
}
