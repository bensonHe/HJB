package com.hjb.spider;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hjb.spider.resource.LianjiaResource;

@Component
public class Start {
	@Autowired
	private LianjiaResource lianjia;

	public void startUp() throws IOException {
		lianjia.getNewPub(1);
	}

	public static void main(String[] args) throws IOException {

	}

}
