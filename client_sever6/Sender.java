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
			// 1、创建本地址端口对象
			localAddr = new InetSocketAddress(InetAddress.getLocalHost()// 获取当地的地址
					.getHostAddress(), 12000);
			// 2.创建发送的Socket对象
			dSender = new DatagramSocket(localAddr);
		} catch (Exception e) {
		}
		// 3、创建对方地址端口对象
		destAdd = new InetSocketAddress("192.168.31.221", 8889);// android端IP地址
		// 常用ip192.168.31.160，223.104.131.253,175.11.28.100,113.246.241.144,
	}

	public void sendImage(byte[] buffer) {

		// 4.创建要发送的数据包,指定内容,指定目标地址
		DatagramPacket dp;
		try {
			// System.out.println(buffer + "," + destAdd);
			dp = new DatagramPacket(buffer, buffer.length, destAdd);
			// 5、发送数据包
			dSender.send(dp);
			// System.out.println("发送后");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
