package client_sever6;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import javax.imageio.ImageIO;
import javax.swing.JTextArea;

public class ReceiveThread extends Thread {
	public int count = 0;
	public JTextArea area;
	public static BufferedImage image;
	public DatagramSocket recvSocket;

	public ReceiveThread(JTextArea area) throws Exception {
		this.area = area;
		// 1、创建接收者对象，传入接收端口
	}

	public void run() {
		try {
			DatagramSocket recvSocket = new DatagramSocket(8888);
			while (true) {
				// 2.指定接收缓冲区大小:每个包40000字节
				byte[] buffer = new byte[40000]; // 3.指定接收缓冲区大小:每个包20字节
				// 3.创建接收数据包对象
				DatagramPacket packet = new DatagramPacket(buffer,
						buffer.length);
				// 4.阻塞等待数据到来,如果收到数据,存入packet中的缓冲区中
				System.out.println("UDP接收前");
				recvSocket.receive(packet);
				System.out.println("UDP接收后");
				// 5、处理接收到的数据
				String msg = new String(buffer, "utf-8").trim();
				area.setText(area.getText() + "\r\n" + msg);
				// System.out.println(msg);
				dataHanlder(buffer);
			}
		} catch (Exception e1) {
		}
	}

	private void dataHanlder(byte[] buffer) throws Exception {
		// 接收的类型为聊天信息
		if (buffer[0] == 1) {
			byte[] copy = new byte[buffer.length - 1];
			System.arraycopy(buffer, 1, copy, 0, copy.length);
			String msg = new String(copy).trim();
			area.setText(area.getText() + "\r\n" + msg);
		}
		// 接收的类型为图片
		// if (buffer[0] == 2) {
		byte[] buffercopy = new byte[buffer.length - 1];
		System.arraycopy(buffer, 1, buffercopy, 0, buffer.length - 1);
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		System.out.println("字节转化为图片");
		image = ImageIO.read(in);
		// }
	}
}
