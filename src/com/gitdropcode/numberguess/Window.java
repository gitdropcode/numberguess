package com.gitdropcode.numberguess;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;

	/*
	 * LAYOUT:
	 * 
	 *     timer    | buttonPane
	 *  ------------------------
	 *     entry    |    log
	 */
	private JPanel timer;
	private JLabel timerLab;
	
	private JPanel buttonPane;
	private JButton startButton;
	private JButton enterButton;
	private JButton ansButton;
	private JButton instButton;
	
	private JPanel entry;
	private JTextField entryField;
	
	private JPanel log;
	private JScrollPane logScroll;
	private JTextArea logArea;
	
	public Window()
	{
		addComponents();
		addButtonFunctionality();
		
		getContentPane().setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);
		
		URL iconURL = getClass().getResource("/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());
		
		setTitle("Guess a Number");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	private void addComponents()
	{
		timerLab = new JLabel("TIMER");
		Font timerFont = timerLab.getFont();
		timerLab.setFont(timerFont.deriveFont(Font.PLAIN, 96.0f));
		
		timer = new JPanel();
		BoxLayout timerLayout = new BoxLayout(timer, BoxLayout.X_AXIS);
		timer.setLayout(timerLayout);
		timer.add(Box.createHorizontalGlue());
		timer.add(timerLab);
		timer.add(Box.createHorizontalGlue());
		
		startButton = new JButton("Start Game");
		enterButton = new JButton("Enter");
		ansButton = new JButton("Show Answer");
		instButton = new JButton("Instructions");
		
		buttonPane = new JPanel();
		BoxLayout buttonLayout = new BoxLayout(buttonPane, BoxLayout.Y_AXIS);
		buttonPane.setLayout(buttonLayout);
		buttonPane.add(Box.createVerticalGlue());
		addAButton(startButton);
		buttonPane.add(Box.createVerticalGlue());
		addAButton(enterButton);
		buttonPane.add(Box.createVerticalGlue());
		addAButton(ansButton);
		buttonPane.add(Box.createVerticalGlue());
		addAButton(instButton);
		buttonPane.add(Box.createVerticalGlue());
		
		entryField = new JTextField();
		entryField.setHorizontalAlignment(JTextField.CENTER);
		Font entryFont = entryField.getFont();
		entryField.setFont(entryFont.deriveFont(Font.PLAIN, 120.0f));
		entryField.setMinimumSize(new Dimension(350, 200));
		entryField.setPreferredSize(new Dimension(350, 200));
		entryField.setMaximumSize(new Dimension(350, 200));
		
		entry = new JPanel();
		BoxLayout entryLayout = new BoxLayout(entry, BoxLayout.X_AXIS);
		entry.setLayout(entryLayout);
		entry.add(Box.createHorizontalGlue());
		entry.add(entryField);
		entry.add(Box.createHorizontalGlue());
		
		logArea = new JTextArea("log");
		logArea.setLineWrap(true);
		logArea.setWrapStyleWord(true);
		logArea.setEditable(false);
		logArea.setFocusable(false);
		Font logFont = logArea.getFont();
		logArea.setFont(logFont.deriveFont(Font.PLAIN, 24.0f));
		logArea.setOpaque(false);
		
		logScroll = new JScrollPane(logArea);
		logScroll.setPreferredSize(new Dimension(400, 300));
		logScroll.setBorder(BorderFactory.createEmptyBorder(0, 0, 0, 0));
		
		log = new JPanel();
		log.add(logScroll);
		
		setLayout(new GridLayout(2, 2));
		add(timer);
		add(buttonPane);
		add(entry);
		add(log);
	}
	
	private void addAButton(JButton button)
	{
		button.setAlignmentX(Component.CENTER_ALIGNMENT);
		Font buttonFont = button.getFont();
		button.setFont(buttonFont.deriveFont(Font.PLAIN, 24.0f));
		button.setMinimumSize(new Dimension(200, 60));
		button.setMaximumSize(new Dimension(200, 60));
		button.setPreferredSize(new Dimension(200, 60));
		buttonPane.add(button);
	}
	
	private void addButtonFunctionality()
	{
		instButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new Instructions();
			}
		});
	}

	public static void main(String[] args)
	{
		new Window();
	}
}
