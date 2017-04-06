package org.hjb.test.conrrunt.lock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Test4ReentrantReadWriteLock {
	private static ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
	private static long count = 0;

	public static void readByReadLock() throws InterruptedException {
		reentrantReadWriteLock.writeLock().lock();
		reentrantReadWriteLock.readLock().lock();
		System.out.println("comming...");
		Thread.sleep(1000);
		reentrantReadWriteLock.readLock().unlock();
		reentrantReadWriteLock.writeLock().unlock();
	}

	public static synchronized void read() throws InterruptedException {
		System.out.println("comming...");
		Thread.sleep(1000);
	}

	static class T1 extends Thread {
		@Override
		public void run() {
			try {
				readByReadLock();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException {
		new T1().start();
		new T1().start();
		new T1().start();
		new T1().start();
		new T1().start();
	}

}
