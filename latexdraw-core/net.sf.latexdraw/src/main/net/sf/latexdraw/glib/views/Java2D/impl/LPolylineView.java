package net.sf.latexdraw.glib.views.Java2D.impl;

import java.util.ArrayList;
import java.util.List;

import net.sf.latexdraw.glib.models.interfaces.shape.IPoint;
import net.sf.latexdraw.glib.models.interfaces.shape.IPolyline;
import net.sf.latexdraw.util.LNumber;

/**
 * Defines a view of the IPolyline model.<br>
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
 * 03/18/2008<br>
 * @author Arnaud BLOUIN
 * @since 3.0
 */
class LPolylineView extends LModifiablePointsShapeView<IPolyline> {
	/**
	 * Creates an initialises the Java view of a LLines.
	 * @param model The model to view.
	 * @since 3.0
	 */
	protected LPolylineView(final IPolyline model) {
		super(model);
		arrows = new ArrayList<>();
		for(int i=0, size=shape.getNbArrows(); i<size; i++)
			arrows.add(new LArrowView(shape.getArrowAt(i)));
		update();
	}


	@Override
	protected void updateGeneralPathMiddle() {
		setPath(false);
	}

	@Override
	protected void setPath(final boolean close) {
		final List<IPoint> pts = shape.getPoints();

		path.reset();

		if(pts.size()>1) {
			final int size 	= pts.size();
			IPoint pt		= pts.get(0);
			// We must check if all the points of the shape are equals because if it
			// is the case the shape will not be visible.
			double firstX = pt.getX();
			double firstY = pt.getY();
			double sumX	= firstX;
			double sumY	= firstY;
			double[] coords = LBezierCurveView.updatePoint4Arrows(firstX, firstY, shape.getArrowAt(0));
			firstX = coords[0];
			firstY = coords[1];

			path.moveTo(firstX, firstY);

			for(int i=1; i<size-1; i++) {
				pt = pts.get(i);
				path.lineTo(pt.getX(), pt.getY());
				// To check the points position, all the X coordinates are added together
				// and at then end we compare if the first X coordinate equals to the average of the X
				// coordinate (idem from Y coordinates). If it is the case, all the point are equals.
				sumX += pt.getX();
				sumY += pt.getY();
			}

			pt = pts.get(pts.size()-1);
			coords = LBezierCurveView.updatePoint4Arrows(pt.getX(), pt.getY(), shape.getArrowAt(-1));
			path.lineTo(coords[0], coords[1]);
			sumX += pt.getX();
			sumY += pt.getY();

			// Checking the equality between points of the shape.
			if(LNumber.equalsDouble(firstX, sumX/size) && LNumber.equalsDouble(firstY, sumY/size)) {
				// If they are all equals, we draw a tiny but visible line.
				path.reset();
				path.moveTo(firstX, firstY);
				path.lineTo(firstX+1, firstY);
			}
			else
				if(close)
					path.closePath();
		}
	}
}
