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
 * ʵ�ַ��롢���ֻ���������
 */
public class SendMain extends JFrame {
	public JTextField field;
	public JTextArea area;
	public Sender sender = new Sender();
	public BufferedImage bi;
	public BufferedImage img;

	public String[] Array = { "����", "Ӣ��", "����", "������", "����", "����", "����", "����",
			"����", "�������", "��������", "Խ����" };

	public static void main(String[] args) throws Exception {
		SendMain c2i = new SendMain();
		c2i.test();
	}

	public void test() throws Exception {
		this.setTitle("������Ƶͨ��ϵͳ1.0.4");
		this.setSize(800, 500);
		this.setDefaultCloseOperation(3);
		// ����������
		JPanel video_panel = new JPanel();
		JPanel panel_west = new JPanel();
		video_panel.setPreferredSize(new Dimension(500, 0));
		panel_west.setPreferredSize(new Dimension(300, 0));
		video_panel.setBackground(Color.cyan);
		// ����ı����������
		area = new JTextArea(20, 20);
		field = new JTextField(20);
		// ��Ӱ�ť
		FoucsListener listener = new FoucsListener(field);
		field.addFocusListener(listener);
		JButton button = new JButton("����");

		JComboBox<String> faceCombo = new JComboBox<>(Array);
		faceCombo.setEditable(true);// ʹ֮�ɱ༭
		faceCombo.setEnabled(true);

		panel_west.add(area);
		panel_west.add(field);
		panel_west.add(button);
		panel_west.add(faceCombo);

		this.add(panel_west, BorderLayout.CENTER);
		this.add(video_panel, BorderLayout.EAST);
		this.setVisible(true);

		Graphics g = video_panel.getGraphics();
		// ���������߳�
		ReceiveThread recv = new ReceiveThread(area);
		recv.start();

		// ��������ͷ������ͼƬ
		ButtonListener buttonListener = new ButtonListener(faceCombo, field,
				area, sender);
		button.addActionListener(buttonListener);
		faceCombo.addActionListener(buttonListener);

		initVedio(g);

	}

	private void initVedio(Graphics g) throws Exception {
		OpenCVFrameGrabber grabber = new OpenCVFrameGrabber(0);
		grabber.start();// ��ʼ��ȡ����ͷ����
		Java2DFrameConverter converter = new Java2DFrameConverter();
		Executor executor = Executors.newCachedThreadPool();// �̳߳�
		while (true) {
			// ȡ��һ����Ƭ
			bi = converter.getBufferedImage(grabber.grabFrame());
			// ��ȡ���յ���ͼƬ
			img = ReceiveThread.image;
			g.drawImage(img, 0, 0, null);
			// g.drawImage(bi, 0, 0, 200, 200, null);
			// ͼƬת�ֽڲ����͸��Է�
			// imgTobyte();
			// Thread.sleep(20);
		}
	}

	private void imgTobyte() throws Exception {
		// �����ֽ����������
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// ��ͼƬд���ֽ����������
		ImageIO.write(bi, "jpg", baos);
		byte[] imageInByte = baos.toByteArray();
		byte[] buffer = new byte[imageInByte.length + 1];
		buffer[0] = 2;// ���ݰ����ͣ�2��ʾͼƬ
		// ��imageInByte�ӵ�0λ��ʼ�����Ƶ�buffer�ӵ�һλ��ʼ
		System.arraycopy(imageInByte, 0, buffer, 1, imageInByte.length);
		// ���÷��ͺ���
		sender.sendImage(buffer);
		baos.flush();// ��ջ���������
		baos.close();
	}

}
