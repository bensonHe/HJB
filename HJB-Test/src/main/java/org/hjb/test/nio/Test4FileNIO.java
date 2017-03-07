package org.hjb.test.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.charset.Charset;

/**
 * nio中的position，limit， captain， mark
 * 
 * @author hejb 2017.03.06
 *
 */
public class Test4FileNIO {

	/**
	 * 通过NIO的byteBuffer去读取文件
	 * 有两个方法注意
	 *   flib()，把limit=position, 把position=0, 把mark失效，mark=-1
	 *  clear()，把limit=captain(即不限制，一般写模式),position=0,mark=-1 
	 * @param fileName
	 * @throws IOException
	 */
	public static void readByByteBuff(String fileName) throws IOException {
		FileInputStream file = null;
		FileChannel channel = null;
		try {
			file = new FileInputStream(fileName);

			channel = file.getChannel();
			ByteBuffer buff = ByteBuffer.allocate(50); // 1G

			Charset charset = Charset.forName("gbk");
			// limit- position
			while (channel.read(buff) != -1) {
				// position=0, limit = now position
				buff.flip();
				System.out.println(charset.decode(buff));
				buff.position(0);
				buff.limit(buff.capacity());
				// buff.clear(); // 等价, 写模式即使：把limit 放大最大， position从0开始
			}

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			channel.close();
			file.close();
		}
	}

	/**
	 * 通过channel的map映射方式来读取文件，其实是通过操作系统的一块文件映射内存操作文件
	 * 
	 * @param fileName
	 * @throws IOException
	 */
	public static void readByMapper(String fileName) throws IOException {
		FileInputStream file = null;
		FileChannel channel = null;
		try {
			file = new FileInputStream(fileName);

			channel = file.getChannel();
			Charset charset = Charset.forName("gbk");
			MappedByteBuffer bb = channel.map(MapMode.READ_WRITE, 0, file.available());
			// bb.flip(); 好像已经flib过了
			System.out.println(bb.position());
			System.out.println(bb.limit());
			System.out.println(bb.capacity());

			System.out.println("affter flib");
			System.out.println(charset.decode(bb));
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			channel.close();
			file.close();
		}
	}

	public static void main(String[] args) throws IOException {
		String fileName = "E://aa//test.txt";
		// readByMapper(fileName);
		readByByteBuff(fileName);
	}
}
