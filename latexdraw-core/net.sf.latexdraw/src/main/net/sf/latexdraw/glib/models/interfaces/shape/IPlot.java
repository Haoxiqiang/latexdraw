package net.sf.latexdraw.glib.models.interfaces.shape;

import net.sf.latexdraw.glib.models.interfaces.prop.IPlotProp;

/**
 * Defines an interface that classes defining a plot should implement.<br>
 * <br>
 * This file is part of LaTeXDraw.<br>
 * Copyright (c) 2005-2014 Arnaud BLOUIN<br>
 * <br>
 * LaTeXDraw is free software; you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later version.
 * <br>
 * LaTeXDraw is distributed without any warranty; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * General Public License for more details.<br>
 * <br>
 * 07/03/2009<br>
 * @author Arnaud BLOUIN
 * @version 3.0
 * @since 3.0
 */
public interface IPlot extends IPositionShape, IPlotProp {
	/**
	 * @param x The X coordinate.
	 * @return The corresponding Y coordinate or NaN if a problem occurs.
	 */
	double getY(final double x);
}
