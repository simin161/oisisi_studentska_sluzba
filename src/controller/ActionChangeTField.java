package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTextField;

public class ActionChangeTField {

	public static void changeTField(JTextField first, JTextField second) {
		first.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				second.requestFocusInWindow();

;			}
			
		});

	}
}
