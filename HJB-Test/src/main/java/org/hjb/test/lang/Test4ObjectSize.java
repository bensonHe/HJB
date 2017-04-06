package org.hjb.test.lang;

public class Test4ObjectSize {
	static class AObject {
		private byte[] aByte = new byte[1024 * 1024];
	}

	public static void main(String[] args) throws InterruptedException {
		AObject aObject = new AObject();
		long startTotalMemory = Runtime.getRuntime().totalMemory();
		aObject = null;
		System.gc();
		System.out.println(Runtime.getRuntime().totalMemory() - startTotalMemory);
	}
}
