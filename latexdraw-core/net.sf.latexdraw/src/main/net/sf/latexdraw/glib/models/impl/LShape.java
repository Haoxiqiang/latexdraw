package net.sf.latexdraw.glib.models.impl;

import java.awt.Color;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

import net.sf.latexdraw.glib.models.GLibUtilities;
import net.sf.latexdraw.glib.models.ShapeFactory;
import net.sf.latexdraw.glib.models.interfaces.shape.IPoint;
import net.sf.latexdraw.glib.models.interfaces.shape.IShape;
import net.sf.latexdraw.glib.views.pst.PSTricksConstants;
import net.sf.latexdraw.util.LNumber;

import org.malai.mapping.MappingRegistry;

/**
 * Defines a model of a shape.<br>
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
 * 07/05/2009<br>
 * @author Arnaud BLOUIN
 * @version 3.0
 * @since 3.0
 */
abstract class LShape implements IShape {
	/** The increment used to define the ID of the shapes. */
	private static int number = 0;

	/** The thickness of the lines of the shape in pixels. */
	protected double thickness;

	/** The colour of the lines. */
	protected Color lineColour;

	/** The style of the lines. */
	protected LineStyle lineStyle;

	/** The white dash separator for dashed lines in pixel. */
	protected double dashSepWhite;

	/** The black dash separator for dashed lines in pixel. */
	protected double dashSepBlack;

	/** The dot separator for dotted lines. */
	protected double dotSep;

	/** The colour of the interior of the shape. */
	protected Color fillingCol;

	/** The style of the interior of the shape. */
	protected FillingStyle fillingStyle;

	/** The start colour of the gradient. */
	protected Color gradColStart;

	/** The end colour of the gradient. */
	protected Color gradColEnd;

	/** The angle of the gradient in radian. */
	protected double gradAngle;

	/** The middle point of the gradient. */
	protected double gradMidPt;

	/** The separation size between hatchings in pixel. */
	protected double hatchingsSep;

	/** The colour of the hatchings. */
	protected Color hatchingsCol;

	/** The angle of the hatchings in radian. */
	protected double hatchingsAngle;

	/** The thickness of the hatchings in pixel. */
	protected double hatchingsWidth;

	/** The rotation angle of the shape. */
	protected double rotationAngle;

	/** Defines if the points of the shape must be considered. */
	protected boolean showPts;

	/** The ID of the shape. */
	protected int id;

	/** Defines if the shape has double borders. */
	protected boolean hasDbleBord;

	/** The colour of the double borders. */
	protected Color dbleBordCol;

	/** The separation size of the double borders in pixel. */
	protected double dbleBordSep;

	/** Defines if the shape has a shadow. */
	protected boolean hasShadow;

	/** The colour of the shadow. */
	protected Color shadowCol;

	/** The angle of the shadow in radian. */
	protected double shadowAngle;

	/** The size of the shadow in pixel. */
	protected double shadowSize;

	/** The position of the border of the shape. */
	protected BorderPos bordersPosition;

	/** The points of the shape. */
	final protected List<IPoint> points;

	/** Defined if the shape has been modified. */
	protected boolean modified;


	/**
	 * The second default constructor
	 * @param uniqueID True: the shape will have a unique ID.
	 */
	protected LShape(final boolean uniqueID) {
		modified		= false;
		thickness		= 2.;
		rotationAngle 	= 0.;
		shadowAngle 	= -Math.PI/4.;
		gradAngle	 	= 0.;
		hatchingsAngle	= 0.;
		hasShadow   	= false;
		hasDbleBord		= false;
		lineStyle		= LineStyle.SOLID;
		lineColour		= PSTricksConstants.DEFAULT_LINE_COLOR;
		dotSep			= PSTricksConstants.DEFAULT_DOT_STEP*PPC;
		dashSepBlack	= PSTricksConstants.DEFAULT_DASH_BLACK*PPC;
		dashSepWhite	= PSTricksConstants.DEFAULT_DASH_WHITE*PPC;
		hatchingsCol	= PSTricksConstants.DEFAULT_HATCHING_COLOR;
		hatchingsSep	= PSTricksConstants.DEFAULT_HATCH_SEP*PPC;
		hatchingsWidth	= PSTricksConstants.DEFAULT_HATCH_WIDTH*PPC;
		fillingStyle	= FillingStyle.NONE;
		fillingCol		= PSTricksConstants.DEFAULT_INTERIOR_COLOR;
		bordersPosition = BorderPos.INTO;
		dbleBordCol		= PSTricksConstants.DEFAULT_DOUBLE_COLOR;
		dbleBordSep		= 6.;
		shadowCol		= PSTricksConstants.DEFAULT_SHADOW_COLOR;
		shadowSize		= PSTricksConstants.DEFAULT_SHADOW_SIZE*PPC;
		gradColStart	= PSTricksConstants.DEFAULT_GRADIENT_START_COLOR;
		gradColEnd		= PSTricksConstants.DEFAULT_GRADIENT_END_COLOR;
		gradMidPt		= PSTricksConstants.DEFAULT_GRADIENT_MID_POINT;
		showPts			= false;
		points			= new ArrayList<>();

		if(uniqueID)
			setNewId();
		else
			setId(0);
	}


	@Override
	public double getFullThickness() {
		return isDbleBorderable() && hasDbleBord() ? getThickness()*2.+getDbleBordSep() : getThickness();
	}


	@Override
	public void copy(final IShape s) {
		if(s==null)
			return ;

		setThickness(s.getThickness());
		setRotationAngle(s.getRotationAngle());
		setShadowAngle(s.getShadowAngle());
		setGradAngle(s.getGradAngle());
		setHatchingsAngle(s.getHatchingsAngle());
		setHasShadow(s.hasShadow());
		setHasDbleBord(s.hasDbleBord());
		setLineStyle(s.getLineStyle());
		setLineColour(s.getLineColour());
		setDotSep(s.getDotSep());
		setDashSepBlack(s.getDashSepBlack());
		setDashSepWhite(s.getDashSepWhite());
		setHatchingsCol(s.getHatchingsCol());
		setHatchingsSep(s.getHatchingsSep());
		setHatchingsWidth(s.getHatchingsWidth());
		setFillingStyle(s.getFillingStyle());
		setFillingCol(s.getFillingCol());
		setBordersPosition(s.getBordersPosition());
		setDbleBordCol(s.getDbleBordCol());
		setDbleBordSep(s.getDbleBordSep());
		setShadowCol(s.getShadowCol());
		setShadowSize(s.getShadowSize());
		setGradColStart(s.getGradColStart());
		setGradColEnd(s.getGradColEnd());
		setGradMidPt(s.getGradMidPt());
		setShowPts(s.isShowPts());

		copyPoints(s);
	}

	protected void copyPoints(final IShape sh) {
		if(sh==null || !getClass().isInstance(sh)) return ;
		points.clear();

		for(final IPoint pt : sh.getPoints())
			points.add(ShapeFactory.createPoint(pt));
	}


	@Override
	public void addToRotationAngle(final IPoint gravCentre, final double angle) {
		if(GLibUtilities.isValidCoordinate(angle)) {
			setRotationAngle(getRotationAngle()+angle);

			if(gravCentre!=null) {
				final IPoint gravityCentre = getGravityCentre();
				final IPoint rotatedGC = gravityCentre.rotatePoint(gravCentre, angle);
				translate(rotatedGC.getX()-gravityCentre.getX(), rotatedGC.getY()-gravityCentre.getY());
			}
		}
	}


	@Override
	public double getBorderGap() {
		double gap;

		switch(bordersPosition) {
			case MID:
				gap = hasDbleBord ? thickness+dbleBordSep/2. : thickness/2.;
				break;

			case OUT:
				gap = hasDbleBord ? thickness*2.+dbleBordSep : thickness;
				break;

			default:
			case INTO:
				gap = 0;
				break;
		}

        return gap;
	}


	@Override
	public BorderPos getBordersPosition() {
		return bordersPosition;
	}


	@Override
	public double getDashSepBlack() {
		return dashSepBlack;
	}


	@Override
	public double getDashSepWhite() {
		return dashSepWhite;
	}


	@Override
	public Color getDbleBordCol() {
		return dbleBordCol;
	}


	@Override
	public double getDbleBordSep() {
		return dbleBordSep;
	}


	@Override
	public double getDotSep() {
		return dotSep;
	}


	@Override
	public Color getFillingCol() {
		return fillingCol;
	}


	@Override
	public FillingStyle getFillingStyle() {
		return fillingStyle;
	}


	@Override
	public IPoint getFullBottomRightPoint() {
		final double gap = getBorderGap();
		final IPoint br  = getBottomRightPoint();

		br.translate(gap, gap);

		return br;
	}



	@Override
	public IPoint getFullTopLeftPoint() {
		final double gap = getBorderGap();
		final IPoint tl  = getTopLeftPoint();

		tl.translate(-gap, -gap);

		return tl;
	}


	@Override
	public double getGradAngle() {
		return gradAngle;
	}


	@Override
	public Color getGradColEnd() {
		return gradColEnd;
	}


	@Override
	public Color getGradColStart() {
		return gradColStart;
	}


	@Override
	public double getGradMidPt() {
		return gradMidPt;
	}


	@Override
	public IPoint getGravityCentre() {
		return points.isEmpty() ? ShapeFactory.createPoint() : getTopLeftPoint().getMiddlePoint(getBottomRightPoint());
	}


	@Override
	public double getHatchingsAngle() {
		return hatchingsAngle;
	}


	@Override
	public Color getHatchingsCol() {
		return hatchingsCol;
	}


	@Override
	public double getHatchingsSep() {
		return hatchingsSep;
	}


	@Override
	public double getHatchingsWidth() {
		return hatchingsWidth;
	}


	@Override
	public int getId() {
		return id;
	}


	@Override
	public Color getLineColour() {
		return lineColour;
	}


	@Override
	public LineStyle getLineStyle() {
		return lineStyle;
	}


	@Override
	public int getNbPoints() {
		return points.size();
	}

	@Override
	public List<IPoint> getPoints() {
		return points;
	}


	@Override
	public IPoint getPtAt(final int position) {
		IPoint point;

		if(points.isEmpty() || position<-1 || position>=points.size())
			point = null;
		else
			point = position==-1 ? points.get(points.size()-1) : points.get(position);

		return point;
	}


	@Override
	public double getRotationAngle() {
		return rotationAngle;
	}


	@Override
	public double getShadowAngle() {
		return shadowAngle;
	}


	@Override
	public Color getShadowCol() {
		return shadowCol;
	}


	@Override
	public double getShadowSize() {
		return shadowSize;
	}


	@Override
	public double getThickness() {
		return thickness;
	}


	@Override
	public boolean hasDbleBord() {
		return hasDbleBord;
	}


	@Override
	public boolean hasGradient() {
		return isInteriorStylable() && fillingStyle==FillingStyle.GRAD;
	}


	@Override
	public boolean hasHatchings() {
		return isInteriorStylable() && fillingStyle.isHatchings();
	}


	@Override
	public boolean hasShadow() {
		return hasShadow;
	}


	@Override
	public boolean isFilled() {
		return fillingStyle.isFilled();
	}


	@Override
	public boolean isShowPts() {
		return showPts;
	}



	@Override
	public boolean isColourable() {
		return true;
	}



//	protected static void scaleX(final List<IPoint> list, final double xRef, final double sx) {
//		for(final IPoint pt : list)
//			if(!LNumber.equalsDouble(xRef, pt.getX()))
//				pt.setX(xRef+(pt.getX()-xRef)*sx);
//	}
//
//	protected static void scaleY(final List<IPoint> list, final double yRef, final double sy) {
//		for(final IPoint pt : list)
//			if(!LNumber.equalsDouble(yRef, pt.getY()))
//				pt.setY(yRef+(pt.getY()-yRef)*sy);
//	}
//
//	protected static void scaleXY(final List<IPoint> list, final IPoint ref, final double sx, final double sy) {
//		final double xRef = ref.getX();
//		final double yRef = ref.getY();
//
//		for(final IPoint pt : list) {
//			if(!LNumber.equalsDouble(xRef, pt.getX()))
//				pt.setX(xRef+(pt.getX()-xRef)*sx);
//			if(!LNumber.equalsDouble(yRef, pt.getY()))
//				pt.setY(yRef+(pt.getY()-yRef)*sy);
//		}
//	}
//
//
//	protected void scaleX(final double xRef, final double sx) {
//		final IPoint tl = getTopLeftPoint();
//		final IPoint br = getBottomRightPoint();
//
//		if(sx>1. || Math.abs(tl.getX()-br.getX())*sx>1.)
//			scaleX(points, xRef, sx);
//	}
//
//
//	protected void scaleY(final double yRef, final double sy) {
//		final IPoint tl = getTopLeftPoint();
//		final IPoint br = getBottomRightPoint();
//
//		if(sy>1. || Math.abs(tl.getY()-br.getY())*sy>1.)
//			scaleY(points, yRef, sy);
//	}
//
//
//	protected void scaleXY(final IPoint ref, final double sx, final double sy) {
//		final IPoint tl = getTopLeftPoint();
//		final IPoint br = getBottomRightPoint();
//
//		if((sx>1. || Math.abs(tl.getX()-br.getX())*sx>1.) && (sy>1. || Math.abs(tl.getY()-br.getY())*sy>1.))
//			scaleXY(points, ref, sx, sy);
//		System.out.println(sx + " " + sy);
//	}


	@Override
	public void setBordersPosition(final BorderPos position) {
		if(position!=null && isBordersMovable())
			bordersPosition = position;
	}


	@Override
	public void setDashSepBlack(final double dashSepBlack) {
		if(dashSepBlack>0 && GLibUtilities.isValidCoordinate(dashSepBlack))
			this.dashSepBlack = dashSepBlack;
	}


	@Override
	public void setDashSepWhite(final double dashSepWhite) {
		if(dashSepWhite>0 && GLibUtilities.isValidCoordinate(dashSepWhite))
			this.dashSepWhite = dashSepWhite;
	}


	@Override
	public void setDbleBordCol(final Color dbleBordCol) {
		if(dbleBordCol!=null && isDbleBorderable())
			this.dbleBordCol = dbleBordCol;
	}


	@Override
	public void setDbleBordSep(final double dbleBordSep) {
		if(dbleBordSep>=0 && isDbleBorderable() && GLibUtilities.isValidCoordinate(dbleBordSep))
			this.dbleBordSep = dbleBordSep;
	}


	@Override
	public void setDotSep(final double dotSep) {
		if(dotSep>=0 && GLibUtilities.isValidCoordinate(dotSep))
			this.dotSep = dotSep;
	}


	@Override
	public void setFilled(final boolean isFilled) {
		if(!isFillable())
			return;

		if(isFilled)
			switch(fillingStyle) {
				case CLINES:
					fillingStyle = FillingStyle.CLINES_PLAIN;
					break;

				case VLINES:
					fillingStyle = FillingStyle.VLINES_PLAIN;
					break;

				case HLINES:
					fillingStyle = FillingStyle.HLINES_PLAIN;
					break;

				case NONE:
					fillingStyle = FillingStyle.PLAIN;
					break;

				case PLAIN:
				case GRAD:
				case CLINES_PLAIN:
				case HLINES_PLAIN:
				case VLINES_PLAIN:
					/* Nothing to do. */
					break;
			}
		else
			switch(fillingStyle) {
				case CLINES_PLAIN:
					fillingStyle = FillingStyle.CLINES;
					break;

				case VLINES_PLAIN:
					fillingStyle = FillingStyle.VLINES;
					break;

				case HLINES_PLAIN:
					fillingStyle = FillingStyle.HLINES;
					break;

				case PLAIN:
					fillingStyle = FillingStyle.NONE;
					break;

				case NONE:
				case GRAD:
				case CLINES:
				case HLINES:
				case VLINES:
					/* Nothing to do. */
					break;
			}
	}


	@Override
	public void setFillingCol(final Color fillingCol) {
		if(fillingCol!=null && isFillable())
			this.fillingCol = fillingCol;
	}


	@Override
	public void setFillingStyle(final FillingStyle fillingStyle) {
		if(fillingStyle!=null && isFillable())
			this.fillingStyle = fillingStyle;
	}


	@Override
	public void setGradAngle(final double gradAngle) {
		if(GLibUtilities.isValidCoordinate(gradAngle) && isInteriorStylable())
			this.gradAngle = gradAngle;
	}


	@Override
	public void setGradColEnd(final Color gradColEnd) {
		if(gradColEnd!=null && isInteriorStylable())
			this.gradColEnd = gradColEnd;
	}


	@Override
	public void setGradColStart(final Color gradColStart) {
		if(gradColStart!=null && isInteriorStylable())
			this.gradColStart = gradColStart;
	}


	@Override
	public void setGradMidPt(final double gradMidPt) {
		if(gradMidPt>=0 && gradMidPt<=1 && isInteriorStylable())
			this.gradMidPt = gradMidPt;
	}


	@Override
	public void setHasDbleBord(final boolean hasDbleBord) {
		if(isDbleBorderable())
			this.hasDbleBord = hasDbleBord;
	}


	@Override
	public void setHasShadow(final boolean hasShadow) {
		if(isShadowable())
			this.hasShadow = hasShadow;
	}


	@Override
	public void setHatchingsAngle(final double hatchingsAngle) {
		if(GLibUtilities.isValidCoordinate(hatchingsAngle) && isInteriorStylable())
			this.hatchingsAngle = hatchingsAngle;
	}


	@Override
	public void setHatchingsCol(final Color hatchingsCol) {
		if(hatchingsCol!=null && isInteriorStylable())
			this.hatchingsCol = hatchingsCol;
	}


	@Override
	public void setHatchingsSep(final double hatchingsSep) {
		if(GLibUtilities.isValidCoordinate(hatchingsSep) && hatchingsSep>=0 && isInteriorStylable())
			this.hatchingsSep = hatchingsSep;
	}


	@Override
	public void setHatchingsWidth(final double hatchingsWidth) {
		if(GLibUtilities.isValidCoordinate(hatchingsWidth) && hatchingsWidth>0 && isInteriorStylable())
			this.hatchingsWidth = hatchingsWidth;
	}


	@Override
	public void setId(final int id) {
		this.id = id;
	}



	@Override
	public void setLineColour(final Color lineColour) {
		if(lineColour!=null)
			this.lineColour = lineColour;
	}


	@Override
	public void setLineStyle(final LineStyle lineStyle) {
		if(lineStyle!=null && isLineStylable())
			this.lineStyle = lineStyle;
	}


	@Override
	public void setNewId() {
		setId(++number);
	}


	@Override
	public void scale(final double x, final double y, final Position pos, final Rectangle2D bound) {
		if(bound==null || pos==null) return ;
		scaleSetPoints(points, x, y, pos, bound);
	}


	protected void scaleSetPoints(final List<IPoint> pts, final double x, final double y, final Position pos, final Rectangle2D bound) {
		final double sx = x/bound.getWidth();
		final double sy = y/bound.getHeight();
		final boolean xScale = pos.isEast() || pos.isWest();
		final boolean yScale = pos.isNorth() || pos.isSouth();
		final double refX = pos.isWest() ? bound.getX() : bound.getMaxX();
		final double refY = pos.isNorth() ? bound.getY() : bound.getMaxY();

		for(final IPoint pt : pts) {
			if(xScale && !LNumber.equalsDouble(pt.getX(), refX))
				pt.setX(refX+(pt.getX()-refX)*sx);
			if(yScale && !LNumber.equalsDouble(pt.getY(), refY))
				pt.setY(refY+(pt.getY()-refY)*sy);
		}
	}




	@Override
	public void setRotationAngle(final double rotationAngle) {
		if(GLibUtilities.isValidCoordinate(rotationAngle))
			this.rotationAngle = rotationAngle;
	}


	@Override
	public void setShadowAngle(final double shadowAngle) {
		if(isShadowable() && GLibUtilities.isValidCoordinate(shadowAngle))
			this.shadowAngle = shadowAngle;
	}


	@Override
	public void setShadowCol(final Color shadowCol) {
		if(shadowCol!=null && isShadowable())
			this.shadowCol = shadowCol;
	}


	@Override
	public void setShadowSize(final double shadowSize) {
		if(isShadowable() && shadowSize>0 && GLibUtilities.isValidCoordinate(shadowSize))
			this.shadowSize = shadowSize;
	}


	@Override
	public void setShowPts(final boolean showPts) {
		if(isShowPtsable())
			this.showPts = showPts;
	}


	@Override
	public void setThickness(final double thickness) {
		if(thickness>0 && isThicknessable() && GLibUtilities.isValidCoordinate(thickness))
			this.thickness = thickness;
	}


	@Override
	public boolean shadowFillsShape() {
		return true;
	}


	@Override
	public void translate(final double tx, final double ty) {
		if(GLibUtilities.isValidPoint(tx, ty))
			for(int i=0, size=points.size(); i<size; i++)
				points.get(i).translate(tx, ty);
	}


	@Override
	public void mirrorHorizontal(final IPoint origin) {
		if(!GLibUtilities.isValidPoint(origin))
			return ;

		IPoint pt1;

		for(final IPoint pt : points) {
			pt1 = pt.horizontalSymmetry(origin);

			if(pt1!=null)
				pt.setPoint(pt1);
		}
	}


	@Override
	public void mirrorVertical(final IPoint origin) {
		if(!GLibUtilities.isValidPoint(origin))
			return ;

		IPoint pt1;

		for(final IPoint pt : points) {
			pt1 = pt.verticalSymmetry(origin);

			if(pt1!=null)
				pt.setPoint(pt1);
		}
	}


	@Override
	public IPoint getBottomRightPoint() {
		final IPoint br = ShapeFactory.createPoint();

		if(points.size()>0) {
			IPoint pt = points.get(0);
			br.setPoint(pt);

			for(int i=1, size=points.size(); i<size; i++) {
				pt = points.get(i);

				if(pt.getX()>br.getX()) br.setX(pt.getX());
				if(pt.getY()>br.getY()) br.setY(pt.getY());
			}
		}

		return br;
	}


	@Override
	public IPoint getBottomLeftPoint() {
		final IPoint bl = ShapeFactory.createPoint();

		if(points.size()>0) {
			IPoint pt = points.get(0);
			bl.setPoint(pt);

			for(int i=1, size=points.size(); i<size; i++) {
				pt = points.get(i);

				if(pt.getX()<bl.getX()) bl.setX(pt.getX());
				if(pt.getY()>bl.getY()) bl.setY(pt.getY());
			}
		}

		return bl;
	}


	@Override
	public IPoint getTopLeftPoint() {
		final IPoint tl = ShapeFactory.createPoint();

		if(points.size()>0) {
			IPoint pt = points.get(0);
			tl.setPoint(pt);

			for(int i=1, size=points.size(); i<size; i++) {
				pt = points.get(i);

				if(pt.getX()<tl.getX()) tl.setX(pt.getX());
				if(pt.getY()<tl.getY()) tl.setY(pt.getY());
			}
		}

		return tl;
	}


	@Override
	public IPoint getTopRightPoint() {
		final IPoint tr = ShapeFactory.createPoint();

		if(points.size()>0) {
			IPoint pt = points.get(0);
			tr.setPoint(pt);

			for(int i=1, size=points.size(); i<size; i++) {
				pt = points.get(i);

				if(pt.getX()>tr.getX()) tr.setX(pt.getX());
				if(pt.getY()<tr.getY()) tr.setY(pt.getY());
			}
		}

		return tr;
	}



	@Override
	public IShape duplicate() {
		final IShape shape = ShapeFactory.newShape(this.getClass()).get();

		shape.copy(this);
		return shape;
	}


	@Override
	public void setModified(final boolean modified) {
		if(modified)
			MappingRegistry.REGISTRY.onObjectModified(this);

		this.modified = modified;
	}


	@Override
	public boolean isModified() {
		return modified;
	}


	@Override
	public void rotate(final IPoint point, final double angle) {
		final IPoint gc = getGravityCentre();

		if(point!=null && !gc.equals(point)) {// The position of the shape must be rotated.
			final IPoint rotGC = gc.rotatePoint(point, angle);
			translate(rotGC.getX() - gc.getX(), rotGC.getY() - gc.getY());
		}

		setRotationAngle(rotationAngle+angle);
	}

	@Override
	public boolean isBordersMovable() {
		return false;
	}


	@Override
	public boolean isDbleBorderable() {
		return false;
	}


	@Override
	public boolean isFillable() {
		return false;
	}


	@Override
	public boolean isInteriorStylable() {
		return false;
	}


	@Override
	public boolean isLineStylable() {
		return false;
	}


	@Override
	public boolean isShadowable() {
		return false;
	}


	@Override
	public boolean isShowPtsable() {
		return false;
	}


	@Override
	public boolean isThicknessable() {
		return false;
	}

	@Override
	public boolean isTypeOf(final Class<?> clazz) {
		if(clazz==null)
			return false;

		return clazz.isInstance(this);
	}


	@Override
	public double getHeight() {
		return Math.abs(getBottomLeftPoint().getY()-getTopLeftPoint().getY());
	}


	@Override
	public double getWidth() {
		return Math.abs(getTopRightPoint().getX()-getTopLeftPoint().getX());
	}
}
