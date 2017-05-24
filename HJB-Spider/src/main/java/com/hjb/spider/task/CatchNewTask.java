package com.hjb.spider.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.hjb.spider.resource.LianjiaResource;
//
//@Component
//@Configurable
//@EnableScheduling
public class CatchNewTask {

	@Autowired
	private LianjiaResource lianjia;

	@Scheduled(cron = "0 56 13 * * ?")
	public void reportCurrentByCron() {
		lianjia.getNewPub(1);
	}

}
