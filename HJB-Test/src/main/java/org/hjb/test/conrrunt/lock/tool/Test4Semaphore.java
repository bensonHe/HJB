package org.hjb.test.conrrunt.lock.tool;

import java.util.concurrent.Semaphore;

/**
 * semaphore 维持固定线程数. 初始化时保存个state =2；
 * 
 * 
 * @author hejinbin 2017.05.22
 *
 */
public class Test4Semaphore {
	public static Semaphore semaphore = new Semaphore(2);

	static class T extends Thread {
		@Override
		public void run() {
			try {
				semaphore.acquire();
				System.out.println("i join ...");
				Thread.sleep(1000);
				semaphore.release();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	public static void main(String[] args) {
		new T().start();
		new T().start();
		new T().start();
		new T().start();
		new T().start();

	}
}
