package org.hjb.test.conrrunt.lock;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.locks.ReentrantLock;

public class Test4CountDownLatch {
	private static ReentrantLock lock = new ReentrantLock();
	private static long count = 0;

	private static CountDownLatch ctd = new CountDownLatch(2);

	public static void addAndSet() {
		lock.lock();
		count++;
		lock.unlock();
	}

	public static long getCount() {
		return count;
	}

	static class T1 extends Thread {
		@Override
		public void run() {
			for (int i = 0; i < 10000; i++) {
				addAndSet();
			}
			ctd.countDown();
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new T1().start();
		new T1().start();
		ctd.await();
		System.out.println(getCount());

	}
}
