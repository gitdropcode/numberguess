package com.gitdropcode.numberguess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class Instructions extends JFrame
{
	private static final long serialVersionUID = 1L;

	private JTextArea instructions;
	private JPanel bottom;
	private JButton button;

	public Instructions()
	{
		addComponents();

		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispatchEvent(new WindowEvent(Instructions.this,
						WindowEvent.WINDOW_CLOSING));
			}
		});

		getContentPane().setPreferredSize(new Dimension(800, 600));
		pack();
		setLocationRelativeTo(null);

		URL iconURL = getClass().getResource("/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());

		setTitle("Guess a Number - Instructions");
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void addComponents()
	{
		StringBuilder text = new StringBuilder();
		text.append("The object of this game is to guess the five digits ");
		text.append("of the computer. Each digit is distinct and the first ");
		text.append("digit is not a 0. Each of your guesses will be analyzed ");
		text.append("and a result will be displayed. Each digit in the ");
		text.append("CORRECT position will score an A, and each correct ");
		text.append("digit in the wrong position will score a B. Even though ");
		text.append("you have 21 guesses, you will be timed. Good luck! ");

		instructions = new JTextArea(text.toString());
		instructions.setLineWrap(true);
		instructions.setWrapStyleWord(true);
		instructions.setEditable(false);
		instructions.setFocusable(false);
		instructions.setPreferredSize(new Dimension(800, 540));
		instructions.setMargin(new Insets(10, 10, 10, 10));

		Font instFont = instructions.getFont();
		instructions.setFont(instFont.deriveFont(Font.PLAIN, 36.0f));
		instructions.setOpaque(false);

		button = new JButton("Close");
		Font buttonFont = button.getFont();
		button.setFont(buttonFont.deriveFont(Font.PLAIN, 24.0f));
		button.setMinimumSize(new Dimension(200, 60));
		button.setMaximumSize(new Dimension(200, 60));
		button.setPreferredSize(new Dimension(200, 60));

		bottom = new JPanel();
		BoxLayout bottomLayout = new BoxLayout(bottom, BoxLayout.X_AXIS);
		bottom.setLayout(bottomLayout);
		bottom.setPreferredSize(new Dimension(800, 60));

		bottom.add(Box.createHorizontalGlue());
		bottom.add(button);

		add(instructions, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);
	}
}
