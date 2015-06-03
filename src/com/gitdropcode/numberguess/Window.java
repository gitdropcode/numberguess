package com.gitdropcode.numberguess;

import java.awt.Dimension;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;

	public Window()
	{
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

	public static void main(String[] args)
	{
		new Window();
	}
}
