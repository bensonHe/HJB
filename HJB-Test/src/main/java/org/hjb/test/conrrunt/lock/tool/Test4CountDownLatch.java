package org.hjb.test.conrrunt.lock.tool;

import java.util.concurrent.CountDownLatch;

/**
 * 原理, 如下面例子解释
 * 
 * new CountDownLatch时，把state设置2,
 * 
 * 调用await时调用acquireSharedInterruptibly.
 * 
 * acquireSharedInterruptibly-> 获取state是否为0，如果不是0，说明还没有调用完成countDown,
 * 则进行入队CHL队列（共享）
 * 
 * 调用countDown时调用的是release释放state。 即 state-1,
 * 直至state为0时调用doReleaseShared()。激活所有等待线程
 * 
 * @author hejinbin 2017.05.22
 *
 */
public class Test4CountDownLatch {
	static CountDownLatch latch = new CountDownLatch(2);

	static class T extends Thread {

		@Override
		public void run() {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("count down");
			latch.countDown();
		}

	};

	public static void main(String[] args) throws InterruptedException {
		new T().start();
		new T().start();
		System.out.println("wait..");
		latch.await();
		System.out.println("done");
	}
}
