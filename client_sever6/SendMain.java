package client_sever6;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.bytedeco.javacv.Java2DFrameConverter;
import org.bytedeco.javacv.OpenCVFrameGrabber;

/**
 * 实现翻译、与手机文字聊天
 */
public class SendMain extends JFrame {
	public JTextField field;
	public JTextArea area;
	public Sender sender = new Sender();
	public BufferedImage bi;
	public BufferedImage img;

	public String[] Array = { "中文", "英语", "粤语", "文言文", "日语", "韩语", "法语", "俄语",
			"德语", "意大利语", "繁体中文", "越南语" };

	public static void main(String[] args) throws Exception {
		SendMain c2i = new SendMain();
		c2i.test();
	}

	public void test() throws Exception {
		this.setTitle("创意视频通信系统1.0.4");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(3);
		// 添加两个面板
		JPanel video_panel = new JPanel();
		JPanel panel_west = new JPanel();
		video_panel.setPreferredSize(new Dimension(500, 0));
		panel_west.setPreferredSize(new Dimension(300, 0));
		video_panel.setBackground(Color.cyan);
		// 添加文本域与输入框
		area = new JTextArea(20, 20);
		field = new JTextField(20);
		// 添加按钮
		FoucsListener listener = new FoucsListener(field);
		field.addFocusListener(listener);
		JButton button = new JButton("发送");

		JComboBox<String> faceCombo = new JComboBox<>(Array);
		faceCombo.setEditable(true);// 使之可编辑
		faceCombo.setEnabled(true);

		panel_west.add(area);
		panel_west.add(field);
		panel_west.add(button);
		panel_west.add(faceCombo);

		this.add(panel_west, BorderLayout.CENTER);
		this.add(video_panel, BorderLayout.EAST);
		this.setVisible(true);

		Graphics g = video_panel.getGraphics();
		// 启动接收线程
		ReceiveThread recv = new ReceiveThread(area);
		recv.start();

		// 开启摄像头并发送图片
		ButtonListener buttonListener = new ButtonListener(faceCombo, field,
				area, sender);
		button.addActionListener(buttonListener);
		faceCombo.addActionListener(buttonListener);

		initVedio(g);

	}

	private void initVedio(Graphics g) throws Exception {
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		grabber.start();// 开始获取摄像头数据
		Java2DFrameConverter converter = new Java2DFrameConverter();
		Executor executor = Executors.newCachedThreadPool();// 线程池
		while (true) {
			// 取得一张照片
			bi = converter.getBufferedImage(grabber.grabFrame());
			// 获取接收到的图片
			img = ReceiveThread.image;
			g.drawImage(img, 0, 0, null);
			// g.drawImage(bi, 0, 0, 200, 200, null);
			// 图片转字节并发送给对方
			// imgTobyte();
			// Thread.sleep(20);
		}
	}

	private void imgTobyte() throws Exception {
		// 创建字节数组输出流
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// 把图片写入字节数组输出流
		ImageIO.write(bi, "jpg", baos);
		byte[] imageInByte = baos.toByteArray();
		byte[] buffer = new byte[imageInByte.length + 1];
		buffer[0] = 2;// 数据包类型，2表示图片
		// 把imageInByte从第0位开始，复制到buffer从第一位开始
		System.arraycopy(imageInByte, 0, buffer, 1, imageInByte.length);
		// 调用发送函数
		sender.sendImage(buffer);
		baos.flush();// 清空缓冲区数据
		baos.close();
	}

}
