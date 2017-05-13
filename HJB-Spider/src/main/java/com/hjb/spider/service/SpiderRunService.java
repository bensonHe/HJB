package com.hjb.spider.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.hjb.spider.entity.HourseInfo;
import com.hjb.spider.resource.LianjiaResource;

public class SpiderRunService {
	@Autowired
	private LianjiaResource resource;

	public void startCatch() {
		List<HourseInfo> listHouses = resource.getNewPub(1);

	}
}
