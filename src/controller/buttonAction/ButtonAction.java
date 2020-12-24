package controller.buttonAction;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import controller.profesor.ProfesoriController;
import model.Profesor;

public class ButtonAction {

	public static void cancelAction(JButton button, JDialog dialog) {

		button.addActionListener(new ActionListener() {
			
			//https://stackoverflow.com/questions/6969164/button-for-closing-a-jdialog
			@Override
			public void actionPerformed(ActionEvent e) {
				dialog.dispose();
			}

		});
	}

	public static void potvrdiButton(JButton button, int dialog) {
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				
				
			}

		});
	}

}
