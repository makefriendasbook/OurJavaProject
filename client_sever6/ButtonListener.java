package client_sever6;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URLDecoder;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import com.alibaba.fastjson.JSON;

public class ButtonListener implements ActionListener {
	public JComboBox<String> comboBox;
	public JTextField field;
	public JTextArea area;
	public String type;
	public static String query;
	public String[] Array = { "中文", "英语", "粤语", "文言文", "日语", "韩语", "法语", "俄语",
			"德语", "意大利语", "繁体中文", "越南语" };
	public String[] P_Array = { "zh", "en", "yue", "wyw", "jp", "kor", "fra",
			"ru", "de", "it", "cht", "vie" };

	public String APP_ID = "20190724000320745";
	public String SECURITY_KEY = "3YZER_NKNmOfbI2wxd6K";
	public TransApi api = new TransApi(APP_ID, SECURITY_KEY);

	public Sender sender;

	public ButtonListener(JComboBox<String> comboBox, JTextField field,
			JTextArea area, Sender sender) {
		super();
		this.comboBox = comboBox;
		this.field = field;
		this.area = area;
		this.sender = sender;

	}

	public void actionPerformed(ActionEvent e) {

		if (e.getSource() == comboBox) {
			JComboBox<String> comboBox = (JComboBox) e.getSource();
			String string = comboBox.getSelectedItem().toString();
			for (int i = 0; i < Array.length; i++) {
				if (string == Array[i]) {
					// System.out.println("类型判断");
					type = P_Array[i];
				}
			}
		}

		if (e.getActionCommand() == "发送") {

			// System.out.println(query + "," + type);
			String str = api.getTransResult(query, "auto", type);
			System.out.println(str);
			String date = "";
			com.alibaba.fastjson.JSONObject jsonObject = JSON.parseObject(str);
			com.alibaba.fastjson.JSONArray array = jsonObject
					.getJSONArray("trans_result");

			int length = array.size();
			String str1 = null;
			for (int i = 0; i < length; i++) {
				com.alibaba.fastjson.JSONObject params = JSON.parseObject(array
						.getString(i));
				str1 = params.getString("dst");
				try {
					str1 = URLDecoder.decode(str1, "utf-8");
					date = str1;
				} catch (Exception e1) {
				}
			}
			System.out.println(date);
			area.setText(area.getText() + "\r\n" + "我：" + date);
			byte[] s = date.getBytes();
			byte[] b = new byte[s.length + 1];
			b[0] = 1;
			System.arraycopy(s, 0, b, 1, s.length);
			sender.sendImage(b);
		}
	}

}
