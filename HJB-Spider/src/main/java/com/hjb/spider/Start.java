package com.hjb.spider;

import java.io.IOException;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hjb.spider.resource.LianjiaResource;
import com.hjb.spider.service.SpiderRunService;

@Component
public class Start {
	@Autowired
	private SpiderRunService spiderRunService;

	public void startUp() throws IOException, InterruptedException {
		for (int i = 11; i < 100; i++) {
			spiderRunService.startCatch(i);
			Thread.sleep(new Random().nextInt(1000));
		}
	}

}
