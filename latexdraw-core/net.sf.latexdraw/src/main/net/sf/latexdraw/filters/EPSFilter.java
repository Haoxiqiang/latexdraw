package net.sf.latexdraw.filters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

/**
 * This class defines a filter for eps documents (*.eps)<br>
 *<br>
 * This file is part of LaTeXDraw<br>
 * Copyright (c) 2005-2014 Arnaud BLOUIN<br>
 *<br>
 *  LaTeXDraw is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.<br>
 *<br>
 *  LaTeXDraw is distributed without any warranty; without even the
 *  implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR
 *  PURPOSE. See the GNU General Public License for more details.<br>
 *<br>
 * 01/20/12<br>
 * @author Arnaud BLOUIN
 * @version 3.0
 */
public class EPSFilter extends FileFilter {
	public static final String EPS_EXTENSION = ".eps";//$NON-NLS-1$


	@Override
	public boolean accept(final File file) {
		return file!=null && (file.getName().endsWith(EPS_EXTENSION)|| file.isDirectory());
	}


	@Override
	public String getDescription() {
		return "*" + EPS_EXTENSION; //$NON-NLS-1$
	}
}
