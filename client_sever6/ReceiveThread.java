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
		// 1�����������߶��󣬴�����ն˿�
	}

	public void run() {
		try {
			DatagramSocket recvSocket = new DatagramSocket(8888);
			while (true) {
				// 2.ָ�����ջ�������С:ÿ����40000�ֽ�
				byte[] buffer = new byte[40000]; // 3.ָ�����ջ�������С:ÿ����20�ֽ�
				// 3.�����������ݰ�����
				DatagramPacket packet = new DatagramPacket(buffer,
						buffer.length);
				// 4.�����ȴ����ݵ���,����յ�����,����packet�еĻ�������
				System.out.println("UDP����ǰ");
				recvSocket.receive(packet);
				System.out.println("UDP���պ�");
				// 5��������յ�������
				String msg = new String(buffer, "utf-8").trim();
				area.setText(area.getText() + "\r\n" + msg);
				// System.out.println(msg);
				dataHanlder(buffer);
			}
		} catch (Exception e1) {
		}
	}

	private void dataHanlder(byte[] buffer) throws Exception {
		// ���յ�����Ϊ������Ϣ
		if (buffer[0] == 1) {
			byte[] copy = new byte[buffer.length - 1];
			System.arraycopy(buffer, 1, copy, 0, copy.length);
			String msg = new String(copy).trim();
			area.setText(area.getText() + "\r\n" + msg);
		}
		// ���յ�����ΪͼƬ
		// if (buffer[0] == 2) {
		byte[] buffercopy = new byte[buffer.length - 1];
		System.arraycopy(buffer, 1, buffercopy, 0, buffer.length - 1);
		ByteArrayInputStream in = new ByteArrayInputStream(buffer);
		System.out.println("�ֽ�ת��ΪͼƬ");
		image = ImageIO.read(in);
		// }
	}
}
