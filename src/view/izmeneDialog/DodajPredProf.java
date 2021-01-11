package view.izmeneDialog;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

import javax.swing.Box;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import controller.buttonAction.ButtonAction;
import controller.profesor.ProfesoriController;
import model.Predmet;
import model.Profesor;
import model.baze.PredmetBaza;
import model.baze.ProfesorBaza;

public class DodajPredProf extends JDialog {

	private static final long serialVersionUID = -1993317580167030278L;

	private int selected = -1;
	
	DodajPredProf(Container c, int selProf){
		
		setSize((int) (c.getWidth() * 0.8), (int) (c.getHeight() * 0.7));
		setModal(true);
		setResizable(false);
		setLayout(new BorderLayout());
		setTitle("Dodaj predmet");
		setLocationRelativeTo(c);
		
		JPanel panelLabel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		JLabel lblPredmeti = new JLabel("Predmeti:");
		panelLabel.add(Box.createHorizontalStrut(20));
		panelLabel.add(lblPredmeti);
		
		Component rigidArea = Box.createRigidArea(new Dimension(15,15));
		add(panelLabel, BorderLayout.NORTH);
		
		add(Box.createHorizontalStrut(20), BorderLayout.WEST);
		add(Box.createHorizontalStrut(20), BorderLayout.EAST);
		
		JPanel panelPrikaz = new JPanel(new BorderLayout());
		
		panelPrikaz.add(rigidArea, BorderLayout.SOUTH);
		
		List<Predmet> predm = PredmetBaza.getInstance().getPredmete();
		Predmet []arr = predm.toArray(new Predmet[0]);
		// https://stackoverflow.com/questions/1018750/how-to-convert-object-array-to-string-array-in-java
		String []strings = Arrays.stream(arr).map(Predmet::toString).toArray(String[]::new);
		
		JList<String> list = new JList<String>(strings);
		
		DefaultListCellRenderer renderer = (DefaultListCellRenderer) list.getCellRenderer();
		renderer.setHorizontalAlignment(SwingConstants.LEFT);
		JScrollPane sp = new JScrollPane(list);
		
		panelPrikaz.add(sp);
		add(panelPrikaz, BorderLayout.CENTER);
	
		JPanel btnPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
		JButton btnPotvrdi = new JButton("Potvrdi");
		JButton btnOdustani = new JButton("Odustani");
		
		btnPanel.add(btnPotvrdi);
		btnPanel.add(Box.createHorizontalStrut(10));
		btnPanel.add(btnOdustani);
		
		ButtonAction.cancelAction(btnOdustani, this);
		
		list.addListSelectionListener(new ListSelectionListener() {

			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				selected = list.getSelectedIndex();
				
			}	
		});
		
		btnPotvrdi.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(selected != -1) {
					
					Profesor p = ProfesorBaza.getInstance().getRow(selProf);
					p.dodajPredmet(arr[selected]);
					ProfesoriController.getInstance().izmeniProfesora(p, p.getBrLicneKarte());
					
					
					
				}
				
			}
			
			
			
		});
		
	}
	
}
