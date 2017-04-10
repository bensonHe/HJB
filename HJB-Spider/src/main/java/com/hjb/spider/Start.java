package com.hjb.spider;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.hjb.spider.resource.Lianjia;

@Component
public class Start {
	@Autowired
	private Lianjia lianjia;

	public void startUp() throws IOException {
		lianjia.catchNew(1);
	}

	public static void main(String[] args) throws IOException {

	}

}
