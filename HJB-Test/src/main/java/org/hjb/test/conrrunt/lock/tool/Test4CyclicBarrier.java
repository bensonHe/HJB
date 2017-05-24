package org.hjb.test.conrrunt.lock.tool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Test4CyclicBarrier {
	static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

	static class T extends Thread {
		@Override
		public void run() {
			try {
				System.out.println("i start");
				Thread.sleep(1000);
				System.out.println("parties is " + cyclicBarrier.getParties());
				cyclicBarrier.await();

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (BrokenBarrierException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException, BrokenBarrierException {
		new T().start();
		cyclicBarrier.await();
		System.out.println("done");
	}
}
