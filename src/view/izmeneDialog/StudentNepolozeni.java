package view.izmeneDialog;

import java.awt.FlowLayout;
import java.util.ArrayList;
import java.util.List;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;

import model.Predmet;
import model.Student;

public class StudentNepolozeni extends JPanel {
	
	private static final long serialVersionUID = 3806457541651770421L;

	private List<Predmet> predmeti = new ArrayList<Predmet>();
	private Student s= null;
	
	public StudentNepolozeni() {
		
		BoxLayout box = new BoxLayout(this, BoxLayout.Y_AXIS);
		setLayout(box);
		
		JPanel panelBtn = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JButton btnDodaj = new JButton("Dodaj");
		JButton btnObrisi = new JButton("Obriši");
		JButton btnPolaganje = new JButton("Polaganje");
		
		
		add(Box.createVerticalStrut(10));
		panelBtn.add(btnDodaj);
		panelBtn.add(btnObrisi);
		panelBtn.add(btnPolaganje);
		
		add(panelBtn);
		add(Box.createVerticalStrut(10));
		
	}
	
	private void updateStudent(Student s) {
		
		this.s = s;
		
	}
	
}
