package org.hjb.test.conrrunt.lock;

import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Executors;

/**
 * 死锁演示
 * 
 * @author hejb 2017.03.07
 *
 */
public class DeadLock {
	public static Object lock1 = new Object();
	public static Object lock2 = new Object();

	public static void main(String[] args) {
		// thread 1
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock1) {

					try {
						System.out.println("waiting for next thread2 to lock1");
						Thread.sleep(1000);
						System.out.println("try to get lock2");
						synchronized (lock2) {
							System.out.println("i 'have in lock2");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();

		// thread 2
		new Thread(new Runnable() {

			@Override
			public void run() {
				synchronized (lock2) {

					try {
						System.out.println("waiting for next thread1 to lock2");
						Thread.sleep(1000);
						System.out.println("try to get lock1");
						synchronized (lock1) {
							System.out.println("i 'have in lock1");
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}

			}
		}).start();
	}

}
