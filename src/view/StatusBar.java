package view;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.border.BevelBorder;

public class StatusBar {
	
	private JPanel panelStatusBar;
	
	StatusBar()
	{
		panelStatusBar = new JPanel();
		panelStatusBar.setBorder(new BevelBorder(BevelBorder.LOWERED));
		panelStatusBar.setLayout(new BoxLayout(panelStatusBar, BoxLayout.X_AXIS));
		panelStatusBar.add(Box.createHorizontalStrut(5));
		
	}
	
	public void attach(Container cont)
	{
		cont.add(panelStatusBar, BorderLayout.SOUTH);
	}
	
	public void addText(String text)
	{
		JLabel labelNaziv = new JLabel(text);
		panelStatusBar.add(labelNaziv);
	}
	
	public void addTimeAndDate()
	{
		JLabel labelTime = new JLabel();
		panelStatusBar.add(Box.createHorizontalGlue());
		
		//https://stackoverflow.com/questions/13366780/how-to-add-real-time-date-and-time-into-a-jframe-component-e-g-status-bar
		
		Timer timer = new Timer(500, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	        	LocalDateTime dateTime = LocalDateTime.now();;
	    		DateTimeFormatter format = DateTimeFormatter.ofPattern(" HH:mm dd.MM.yyyy.");
	    		String formattedDate = dateTime.format(format);
	        	labelTime.setText(formattedDate);
	        }
	    });
		
	    timer.setRepeats(true);
	    timer.setCoalesce(true);
	    timer.setInitialDelay(0);
	    timer.start();
		
		panelStatusBar.add(labelTime);
		
	}

}