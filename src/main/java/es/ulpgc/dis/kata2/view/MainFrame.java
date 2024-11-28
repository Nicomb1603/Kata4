package es.ulpgc.dis.kata2.view;

import es.ulpgc.dis.kata2.control.SQLiteTitleReader;
import es.ulpgc.dis.kata2.model.Histogram;
import es.ulpgc.dis.kata2.model.Title;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	JFreeChartHistogramDisplay display;
	JLabel label;
	SQLiteTitleReader titleReader;

	public MainFrame() throws HeadlessException {
		this.setTitle("es.ulpgc.dis.kata2.model.Histogram Display");
		this.setSize(1920, 1080);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.display = new JFreeChartHistogramDisplay();
		JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
		JButton button = createButton();
		label = createLabel();
		this.setLayout(new BorderLayout());
		topPanel.add(button);
		topPanel.add(label);
		add(topPanel, BorderLayout.NORTH);
		add(display, BorderLayout.CENTER);
		//display.add(new JButton("What to watch?"));
	}

	private JLabel createLabel() {
		return new JLabel();
	}

	public void displayHistogram(Histogram histogram) {
		display.display(histogram);
	}

	private JButton createButton(){
		JButton button = new JButton("What to watch?");
		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				getRandomTitle();
			}
		});
		return button;
	}

	private void getRandomTitle() {
		Title title = titleReader.getRandomTitle();
		label.setText("name: " + title.primaryTitle() + " Title Type: " + title.titleType());
	}

	public void setTitleReader(SQLiteTitleReader titleReader){
		this.titleReader = titleReader;
	}
}
