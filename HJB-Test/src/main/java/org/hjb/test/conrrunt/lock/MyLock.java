package org.hjb.test.conrrunt.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 2017.03.16
 * 
 * @author hejb
 * 
 *         伪代码实现各种类型的锁帮助理解说明
 *
 */
public class MyLock {
	private static AtomicInteger state = new AtomicInteger(0);

	/**
	 * 自旋锁方式去实现阻塞
	 * 
	 * 缺点：无法实现公平性，如果大量使用会增加CPU的Cache一致性流量开销
	 */
	public static void CASLock() {
		// 不断去获取CAS的锁，如成功表示获取锁成功
		while (state.compareAndSet(0, 1)) {
		}
	}

	public static void CASUnlock() {

		if (!state.compareAndSet(1, 0)) {
			// 释放锁异常
			throw new RuntimeException();
		}
	}

}
