package net.sf.latexdraw.glib.models.interfaces.prop;

/**
 * Text shape properties.
 * <br>
 * This file is part of LaTeXDraw<br>
 * Copyright (c) 2005-2014 Arnaud BLOUIN<br>
 *<br>
 *  LaTeXDraw is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  any later version.<br>
 *<br>
 *  LaTeXDraw is distributed without any warranty; without even the
 *  implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *  PURPOSE. See the GNU General Public License for more details.<br>
 *<br>
 */
public interface ITextProp {
	/** This enumeration defines the concept of text size for latex texts. */
	public static enum TextSize {
		/** \tiny size */
		TINY {@Override public String getLatexToken() {return "tiny";}},
		/** \scriptsize size */
		SCRIPT {@Override public String getLatexToken() {return "scriptsize";}},
		/** \footnotesize size */
		FOOTNOTE {@Override public String getLatexToken() {return "footnotesize";}},
		/** \small size */
		SMALL {@Override public String getLatexToken() {return "small";}},
		/** \small size */
		NORMAL {@Override public String getLatexToken() {return "normalsize";}},
		/** \large size */
		LARGE1 {@Override public String getLatexToken() {return "large";}},
		/** \Large size */
		LARGE2 {@Override public String getLatexToken() {return "Large";}},
		/** \LARGE size */
		LARGE3 {@Override public String getLatexToken() {return "LARGE";}},
		/** \huge size */
		HUGE1 {@Override public String getLatexToken() {return "huge";}},
		/** \Huge size */
		HUGE2 {@Override public String getLatexToken() {return "Huge";}};

		/**
		 * @return The latex token corresponding to the text size.
		 * @since 3.0
		 */
		public abstract String getLatexToken();

		/**
		 * @param size The text size value to analyse.
		 * @return The corresponding text size item or null.
		 * @since 3.0
		 */
		public static TextSize getTextSizeFromSize(final int size) {
			switch(size){
				case 11: return TINY;
				case 16: return FOOTNOTE;
				case 35: return HUGE1;
				case 44: return HUGE2;
				case 22: return LARGE1;
				case 24: return LARGE2;
				case 30: return LARGE3;
				case 18: return NORMAL;
				case 14: return SCRIPT;
				case 17: return SMALL;
				default: return null;
			}
		}
	}

	/** The position of the text (bottom-right, top-left, etc.). */
	public static enum TextPosition {
		BOT_LEFT 	{@Override public String getLatexToken() {return "bl";}}, //$NON-NLS-1$
		BOT 		{@Override public String getLatexToken() {return "b";}}, //$NON-NLS-1$
		BOT_RIGHT 	{@Override public String getLatexToken() { return "br";}}, //$NON-NLS-1$
		TOP_LEFT 	{@Override public String getLatexToken() {return "tl";}}, //$NON-NLS-1$
		TOP 		{@Override public String getLatexToken() {return "t";}}, //$NON-NLS-1$
		TOP_RIGHT 	{@Override public String getLatexToken() {return "tr";}}, //$NON-NLS-1$
		BASE 		{@Override public String getLatexToken() { return "B";}}, //$NON-NLS-1$
		BASE_LEFT 	{@Override public String getLatexToken() {return "Bl";}}, //$NON-NLS-1$
		BASE_RIGHT 	{@Override public String getLatexToken() { return "Br";}}, //$NON-NLS-1$
		LEFT 		{@Override public String getLatexToken() {return "l";}}, //$NON-NLS-1$
		RIGHT 		{@Override public String getLatexToken() {return "r";}}, //$NON-NLS-1$
		CENTER 		{@Override public String getLatexToken() {return "";}}; //$NON-NLS-1$

		/**
		 * @return The latex token corresponding to the text position.
		 * @since 3.0
		 */
		public abstract String getLatexToken();

		/**
		 * @param latexToken The latex token to test.
		 * @return The TextPosition enumeration item corresponding to the given latex token.
		 * @since 3.0
		 */
		public static TextPosition getTextPosition(final String latexToken) {
			TextPosition textPos = null;
			final TextPosition[] textPosList = values();

			for(int i=0; i<textPosList.length && textPos==null; i++)
				if(textPosList[i].getLatexToken().equals(latexToken))
					textPos = textPosList[i];

			return textPos;
		}
	}

	/**
	 * @return The text position of the text (bottom-right, top-left, etc.).
	 * @since 3.0
	 */
	TextPosition getTextPosition();

	/**
	 * Sets the text position of the text.
	 * @param textPosition The new text position of the text.
	 * @since 3.0
	 */
	void setTextPosition(final TextPosition textPosition);

	/**
	 * @return the text.
	 * @since 3.0
	 */
	String getText();

	/**
	 * @param text the text to set.
	 * @since 3.0
	 */
	void setText(final String text);
}
