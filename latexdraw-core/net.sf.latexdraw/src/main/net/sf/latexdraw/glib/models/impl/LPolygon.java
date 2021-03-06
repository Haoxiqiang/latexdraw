package net.sf.latexdraw.glib.models.impl;

import net.sf.latexdraw.glib.models.interfaces.shape.IPoint;
import net.sf.latexdraw.glib.models.interfaces.shape.IPolygon;

/**
 * Defines a model of a polygon.<br>
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
 * 02/13/2008<br>
 * @author Arnaud BLOUIN
 * @version 3.0
 * @since 3.0
 */
class LPolygon extends LModifiablePointsShape implements IPolygon {

	/**
	 * Creates a model with no point.
	 * @param uniqueID True: the shape will have a unique ID.
	 */
	protected LPolygon(final boolean uniqueID) {
		super(uniqueID);
	}


	/**
	 * Creates a model with two points.
	 * @param uniqueID True: the shape will have a unique ID.
	 * @throws IllegalArgumentException If one of the two points is null.
	 */
	protected LPolygon(final IPoint point, final IPoint point2, final boolean uniqueID) {
		this(uniqueID);

		if(point==null || point2==null)
			throw new IllegalArgumentException();

		addPoint(point);
		addPoint(point2);
	}

	@Override
	public boolean isDbleBorderable() {
		return true;
	}


	@Override
	public boolean isFillable() {
		return true;
	}


	@Override
	public boolean isInteriorStylable() {
		return true;
	}


	@Override
	public boolean isLineStylable() {
		return true;
	}


	@Override
	public boolean isShadowable() {
		return true;
	}


	@Override
	public boolean isThicknessable() {
		return true;
	}
}
