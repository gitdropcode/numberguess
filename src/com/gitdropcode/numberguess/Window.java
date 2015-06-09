package com.gitdropcode.numberguess;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.Timer;

public class Window extends JFrame
{
	private static final long serialVersionUID = 1L;

	/*
	 * LAYOUT:
	 * 
	 * timer | buttonPane ------------------------ entry | log
	 */
	private JPanel stats;
	private JLabel timer;
	private JLabel guessLab;

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

	private boolean running = false;
	private Random random = new Random();
	private String num;
	private int secs;
	private int guesses;
	private Timer update = new Timer(1000, new ActionListener()
	{
		@Override
		public void actionPerformed(ActionEvent e)
		{
			secs++;
			updateTimer();
		}
	});

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
		timer = new JLabel(" ");
		Font timerFont = timer.getFont();
		timer.setFont(timerFont.deriveFont(Font.PLAIN, 96.0f));
		timer.setAlignmentX(JComponent.CENTER_ALIGNMENT);
		
		guessLab = new JLabel(" ");
		Font guessFont = guessLab.getFont();
		guessLab.setFont(guessFont.deriveFont(Font.PLAIN, 24.0f));
	    guessLab.setAlignmentX(JComponent.CENTER_ALIGNMENT);

		stats = new JPanel();
		BoxLayout timerLayout = new BoxLayout(stats, BoxLayout.Y_AXIS);
		stats.setLayout(timerLayout);
		stats.add(Box.createVerticalGlue());
		stats.add(timer);
		stats.add(Box.createVerticalGlue());
		stats.add(guessLab);
		stats.add(Box.createVerticalGlue());

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

		logArea = new JTextArea();
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
		add(stats);
		add(buttonPane);
		add(entry);
		add(log);

		entryField.setEnabled(false);
		enterButton.setEnabled(false);
		ansButton.setEnabled(false);
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
		startButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				startGame();
			}
		});
		enterButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				guess();
			}
		});
		ansButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				giveUp();
			}
		});
		instButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new Instructions();
			}
		});
	}

	private void startGame()
	{
		running = true;
		secs = 0;
		guesses = 0;

		StringBuilder nums = new StringBuilder();
		List<String> digits = new ArrayList<String>(Arrays.asList("0", "1",
				"2", "3", "4", "5", "6", "7", "8", "9"));

		nums.append(digits.remove(random.nextInt(9) + 1));
		for (int i = 0; i < 4; i++)
		{
			nums.append(digits.remove(random.nextInt(digits.size())));
		}

		num = nums.toString();

		entryField.setEnabled(true);
		entryField.setText("");
		logArea.setText("");
		guessLab.setText("Number of Guesses: 0");
		enterButton.setEnabled(true);
		ansButton.setEnabled(true);

		updateTimer();
		update.start();
	}

	private void endGame()
	{
		running = false;
		update.stop();
		entryField.setEnabled(false);
		enterButton.setEnabled(false);
		ansButton.setEnabled(false);
	}

	private void guess()
	{
		if (!running)
		{
			return;
		}
		String g = entryField.getText();
		if (g.length() == 5)
		{
			entryField.setText("");
			guesses++;

			ArrayList<Character> nlist = new ArrayList<Character>();
			for (char c : num.toCharArray())
			{
				nlist.add(c);
			}

			int a = 0;
			int b = 0;

			for (int i = 0; i < 5; i++)
			{
				if (nlist.get(i) == g.charAt(i))
				{
					a++;
				}
				else if (nlist.contains(g.charAt(i)))
				{
					b++;
				}
			}

			logArea.setText("Guess " + guesses + ": " + g + "\n" + a
					+ " digit scored A and " + b + " digit scored B\n\n"
					+ logArea.getText());
			logArea.setCaretPosition(0);
			guessLab.setText("Number of Guesses: " + guesses);
			if (g.equals(num))
			{
				new DialogBox("Congratulations! You guessed the number!",
						"Congratulations", new Dimension(400, 200));
				endGame();
				return;
			}
			if (guesses >= 20)
			{
				new DialogBox("You used up your 20 guesses.", "Game Over",
						new Dimension(400, 200));
				endGame();
				return;
			}
		}
		else
		{
			new DialogBox("Your guess must be 5 digits.", "Invalid",
					new Dimension(400, 200));
		}
	}

	private void giveUp()
	{
		endGame();
		entryField.setText(num);
	}

	private void updateTimer()
	{
		int seconds = secs % 60;
		int minutes = secs / 60;
		timer.setText(String.format("%d:%02d", minutes, seconds));
	}

	public static void main(String[] args)
	{
		new Window();
	}
}
