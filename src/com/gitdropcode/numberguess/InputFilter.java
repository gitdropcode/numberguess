package com.gitdropcode.numberguess;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class InputFilter extends DocumentFilter
{
	private String s = "";
	private String temps = s;

	@Override
	public void insertString(FilterBypass fb, int offset, String string,
			AttributeSet attr) throws BadLocationException
	{
		temps = temps.substring(0, offset) + string
				+ temps.substring(offset, temps.length());
		super.insertString(fb, offset, string, attr);
	}

	@Override
	public void remove(FilterBypass fb, int offset, int length)
			throws BadLocationException
	{
		replace(fb, offset, length, "", null);
	}

	@Override
	public void replace(FilterBypass fb, int offset, int length, String text,
			AttributeSet attrs) throws BadLocationException
	{
		temps = temps.substring(0, offset) + text
				+ temps.substring(offset + length, temps.length());
		if (!validate(temps))
		{
			temps = s;
		}
		else
		{
			super.replace(fb, offset, length, text, attrs);
			s = temps;
		}
	}

	private boolean validate(String str)
	{
		if (str.equals(""))
		{
			return true;
		}
		if (str.length() > 5)
		{
			return false;
		}
		if ("123456789".indexOf(str.charAt(0)) == -1)
		{
			return false;
		}
		for (int i = 1; i < str.length(); i++)
		{
			if ("0123456789".indexOf(str.charAt(i)) == -1)
			{
				return false;
			}
		}
		List<Character> strlist = new ArrayList<Character>();
		for (char c : str.toCharArray())
		{
			strlist.add(c);
		}

		if ((new HashSet<Character>(strlist)).size() == str.length())
		{
			return true;
		}
		return false;
	}
}
