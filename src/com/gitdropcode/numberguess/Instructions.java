package com.gitdropcode.numberguess;

import java.awt.Dimension;

public class Instructions extends DialogBox
{
	private static final long serialVersionUID = 1L;

	public Instructions(Window parent)
	{
		super(getText(), "Guess A Number - Instructions", new Dimension(800,
				600), parent);
	}

	private static String getText()
	{
		StringBuilder text = new StringBuilder();
		text.append("The object of this game is to guess the five digits ");
		text.append("of the computer. Each digit is distinct and the first ");
		text.append("digit is not a 0. Each of your guesses will be analyzed ");
		text.append("and a result will be displayed. Each digit in the ");
		text.append("CORRECT position will score an A, and each correct ");
		text.append("digit in the wrong position will score a B. Even though ");
		text.append("you have 20 guesses, you will be timed. Good luck! ");

		return text.toString();
	}
}
