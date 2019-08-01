package client_sever6;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Sender {
	public DatagramSocket dSender;
	public SocketAddress localAddr;
	public SocketAddress destAdd;

	public Sender() {
		try {
			// 1����������ַ�˿ڶ���
			localAddr = new InetSocketAddress(InetAddress.getLocalHost()// ��ȡ���صĵ�ַ
					.getHostAddress(), 12000);
			// 2.�������͵�Socket����
			dSender = new DatagramSocket(localAddr);
		} catch (Exception e) {
		}
		// 3�������Է���ַ�˿ڶ���
		destAdd = new InetSocketAddress("192.168.31.221", 8889);// android��IP��ַ
		// ����ip192.168.31.160��223.104.131.253,175.11.28.100,113.246.241.144,
	}

	public void sendImage(byte[] buffer) {

		// 4.����Ҫ���͵����ݰ�,ָ������,ָ��Ŀ���ַ
		DatagramPacket dp;
		try {
			// System.out.println(buffer + "," + destAdd);
			dp = new DatagramPacket(buffer, buffer.length, destAdd);
			// 5���������ݰ�
			dSender.send(dp);
			// System.out.println("���ͺ�");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
