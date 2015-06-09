package com.gitdropcode.numberguess;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class DialogBox extends JFrame
{
	private static final long serialVersionUID = 1L;

	private Dimension size;
	private String s;
	private JTextArea textarea;
	private JPanel bottom;
	private JButton button;
	private Window parent;

	public DialogBox(String s, String title, Dimension size, Window parent)
	{
		this.size = size;
		this.s = s;
		this.parent = parent;
		
		addComponents();

		button.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				dispatchEvent(new WindowEvent(DialogBox.this,
						WindowEvent.WINDOW_CLOSING));
			}
		});
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosed(WindowEvent e)
			{
				DialogBox.this.parent.dialogClosed();
			}
		});

		getContentPane().setPreferredSize(size);
		pack();
		setLocationRelativeTo(null);

		URL iconURL = getClass().getResource("/icon.png");
		ImageIcon icon = new ImageIcon(iconURL);
		setIconImage(icon.getImage());

		setTitle(title);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void addComponents()
	{
		textarea = new JTextArea(s);
		textarea.setLineWrap(true);
		textarea.setWrapStyleWord(true);
		textarea.setEditable(false);
		textarea.setFocusable(false);
		textarea.setPreferredSize(new Dimension(size.width, size.height-60));
		textarea.setMargin(new Insets(10, 10, 10, 10));

		Font instFont = textarea.getFont();
		textarea.setFont(instFont.deriveFont(Font.PLAIN, 36.0f));
		textarea.setOpaque(false);

		button = new JButton("Close");
		Font buttonFont = button.getFont();
		button.setFont(buttonFont.deriveFont(Font.PLAIN, 24.0f));
		button.setMinimumSize(new Dimension(200, 60));
		button.setMaximumSize(new Dimension(200, 60));
		button.setPreferredSize(new Dimension(200, 60));

		bottom = new JPanel();
		BoxLayout bottomLayout = new BoxLayout(bottom, BoxLayout.X_AXIS);
		bottom.setLayout(bottomLayout);
		bottom.setPreferredSize(new Dimension(size.width, 60));

		bottom.add(Box.createHorizontalGlue());
		bottom.add(button);

		add(textarea, BorderLayout.NORTH);
		add(bottom, BorderLayout.SOUTH);
	}
}
