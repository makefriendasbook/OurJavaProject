package client_sever6;

import java.awt.Color;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

import javax.swing.JComponent;
import javax.swing.JTextField;

public class FoucsListener extends FocusAdapter {
	public JTextField field;

	public FoucsListener(JTextField field) {
		this.field = field;
	}

	public void focusGained(FocusEvent e) {
		JComponent component = (JComponent) e.getSource();
		if (component == field) {
			field.setText("");
			field.setForeground(Color.BLACK);
		}
	}

	public void focusLost(FocusEvent e) {
		JComponent component = (JComponent) e.getSource();
		if (component == field) {
			ButtonListener.query = field.getText().toString();
			field.setForeground(Color.gray);
			field.setText("想说点什么");
		}
	}
}
