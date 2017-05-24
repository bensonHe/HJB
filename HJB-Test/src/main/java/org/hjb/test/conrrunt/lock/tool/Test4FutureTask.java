package org.hjb.test.conrrunt.lock.tool;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * FutureTask 一直等待完成任务再返回
 * 
 * 原理： FutureTask->继承自 Runnable, 装饰了run方法，加了状态可以返回
 * 
 * 类似，全局变量outcome保存结果，覆盖了 
 * run(){
 *  加入状态维护代码...
 *  outcome=call()
 * }
 * 
 * 调用get时直接判断状态是否返回结果outcome
 * 
 * 
 * @author hejinbin 2017.05.22
 *
 */
public class Test4FutureTask {
	public static void main(String[] args) throws InterruptedException, ExecutionException {

		FutureTask<Object> futureTask = new FutureTask<>(new Callable<Object>() {
			@Override
			public Object call() throws Exception {
				Thread.sleep(1000);
				return "hahaha...";
			}
		});
		new Thread(futureTask).start();
		System.out.println(futureTask.get());
	}
}
