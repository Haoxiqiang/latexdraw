package test.instruments;

import static org.junit.Assert.assertTrue;

import java.awt.Color;
import java.awt.event.MouseEvent;

import net.sf.latexdraw.actions.shape.AddShape;
import net.sf.latexdraw.glib.models.ShapeFactory;
import net.sf.latexdraw.glib.models.interfaces.prop.IDotProp.DotStyle;
import net.sf.latexdraw.glib.models.interfaces.shape.ICircle;
import net.sf.latexdraw.glib.models.interfaces.shape.IDrawing;
import net.sf.latexdraw.glib.models.interfaces.shape.IEllipse;
import net.sf.latexdraw.glib.models.interfaces.shape.IRectangle;
import net.sf.latexdraw.glib.models.interfaces.shape.IShape;
import net.sf.latexdraw.glib.models.interfaces.shape.ISquare;
import net.sf.latexdraw.glib.models.interfaces.shape.IShape.BorderPos;
import net.sf.latexdraw.glib.models.interfaces.shape.IShape.FillingStyle;
import net.sf.latexdraw.glib.models.interfaces.shape.IShape.LineStyle;
import net.sf.latexdraw.glib.ui.LCanvas;
import net.sf.latexdraw.glib.ui.LMagneticGrid;
import net.sf.latexdraw.glib.views.Java2D.impl.LViewsFactory;
import net.sf.latexdraw.glib.views.Java2D.interfaces.View2DTK;
import net.sf.latexdraw.instruments.EditionChoice;
import net.sf.latexdraw.instruments.Pencil;
import net.sf.latexdraw.instruments.TextSetter;

import org.junit.Before;
import org.junit.Test;
import org.malai.instrument.Link;
import org.malai.swing.instrument.library.WidgetZoomer;
import org.malai.swing.widget.MLayeredPane;

import test.HelperTest;

public class TestPencil extends TestInstrument<Pencil> {
	protected LCanvas canvas;

	@Before
	@SuppressWarnings("unused")
	public void setUp() {
		View2DTK.setFactory(new LViewsFactory());
		IDrawing drawing = ShapeFactory.createDrawing();
		MLayeredPane layers = new MLayeredPane(false, false);
		canvas 		= new LCanvas(drawing);
		LMagneticGrid grid = new LMagneticGrid(canvas);
		WidgetZoomer zoomer = new WidgetZoomer(canvas, true, true, null, "", null, "", true);
		instrument 	= new Pencil(canvas, new TextSetter(layers), layers);
		instrument.addEventable(canvas);
	}


	@Test
	public void testExceptionConstructor() {//FIXME
//		try {
//			new Pencil(null, new Zoomer(new LCanvas()));
//			fail();
//		}catch(IllegalArgumentException ex) { /* ok */ }
//		try {
//			new Pencil(null, null);
//			fail();
//		}catch(IllegalArgumentException ex) { /* ok */ }
//		try {
//			new Pencil(new LDrawing(), null);
//			fail();
//		}catch(IllegalArgumentException ex) { /* ok */ }
	}


	@Test
	public void testLinkDnD2AddShape_PressEventCreatesRectangle() {
		instrument.setActivated(true);
		double x = 100.+canvas.getOrigin().getX(), y = 300.+canvas.getOrigin().getY();
		changePencilShapeAttributes();
		instrument.setCurrentChoice(EditionChoice.RECT);
		canvas.getEventManager().mousePressed(new MouseEvent(canvas, 0, 100, 0, (int)x, (int)y, 0, 0, 0, false, MouseEvent.BUTTON1));
		Link<?,?,?> link = getLink("DnD2AddShape");

		assertTrue(link.getAction() instanceof AddShape);
		assertTrue(((AddShape)link.getAction()).shape().get() instanceof IRectangle);

		IRectangle rec = (IRectangle) ((AddShape)link.getAction()).shape().get();
		checkShape(rec);
		HelperTest.assertEqualsDouble(100., rec.getTopLeftPoint().getX());
		HelperTest.assertEqualsDouble(300., rec.getTopLeftPoint().getY());
		assertTrue(rec.getWidth()>0);
		assertTrue(rec.getHeight()>0);
	}



	@Test
	public void testLinkDnD2AddShape_PressEventCreatesEllipse() {
		instrument.setActivated(true);
		double x = 100.+canvas.getOrigin().getX(), y = 300.+canvas.getOrigin().getY();
		changePencilShapeAttributes();
		instrument.setCurrentChoice(EditionChoice.ELLIPSE);
		canvas.getEventManager().mousePressed(new MouseEvent(canvas, 0, 100, 0, (int)x, (int)y, 0, 0, 0, false, MouseEvent.BUTTON1));
		Link<?,?,?> link = getLink("DnD2AddShape");

		assertTrue(link.getAction() instanceof AddShape);
		assertTrue(((AddShape)link.getAction()).shape().get() instanceof IEllipse);

		IEllipse ell = (IEllipse) ((AddShape)link.getAction()).shape().get();
		checkShape(ell);
		HelperTest.assertEqualsDouble(100., ell.getTopLeftPoint().getX());
		HelperTest.assertEqualsDouble(300., ell.getTopLeftPoint().getY());
		assertTrue(ell.getWidth()>0);
		assertTrue(ell.getHeight()>0);
	}



	@Test
	public void testLinkDnD2AddShape_PressEventCreatesCircle() {
		instrument.setActivated(true);
		double x = 100.+canvas.getOrigin().getX(), y = 300.+canvas.getOrigin().getY();
		changePencilShapeAttributes();
		instrument.setCurrentChoice(EditionChoice.CIRCLE);
		canvas.getEventManager().mousePressed(new MouseEvent(canvas, 0, 100, 0, (int)x, (int)y, 0, 0, 0, false, MouseEvent.BUTTON1));
		Link<?,?,?> link = getLink("DnD2AddShape");

		assertTrue(link.getAction() instanceof AddShape);
		assertTrue(((AddShape)link.getAction()).shape().get() instanceof ICircle);

		ICircle circle = (ICircle) ((AddShape)link.getAction()).shape().get();
		checkShape(circle);
		HelperTest.assertEqualsDouble(100., circle.getGravityCentre().getX());
		HelperTest.assertEqualsDouble(300., circle.getGravityCentre().getY());
		assertTrue(circle.getWidth()>0);
		assertTrue(circle.getHeight()>0);
	}



	@Test
	public void testLinkDnD2AddShape_PressEventCreatesSquare() {
		instrument.setActivated(true);
		double x = 100.+canvas.getOrigin().getX(), y = 300.+canvas.getOrigin().getY();
		changePencilShapeAttributes();
		instrument.setCurrentChoice(EditionChoice.SQUARE);
		canvas.getEventManager().mousePressed(new MouseEvent(canvas, 0, 100, 0, (int)x, (int)y, 0, 0, 0, false, MouseEvent.BUTTON1));
		Link<?,?,?> link = getLink("DnD2AddShape");

		assertTrue(link.getAction() instanceof AddShape);
		assertTrue(((AddShape)link.getAction()).shape().get() instanceof ISquare);

		ISquare square = (ISquare) ((AddShape)link.getAction()).shape().get();
		checkShape(square);
		HelperTest.assertEqualsDouble(100., square.getGravityCentre().getX());
		HelperTest.assertEqualsDouble(300., square.getGravityCentre().getY());
		assertTrue(square.getWidth()>0);
		assertTrue(square.getHeight()>0);
	}



	public void changePencilShapeAttributes() {
		instrument.setActivated(true);
		instrument._groupParams().setLineColour(Color.BLUE);
		instrument._groupParams().setThickness(13.);
		instrument._groupParams().setBordersPosition(BorderPos.OUT);
		instrument._groupParams().setDotStyle(DotStyle.BAR);
		instrument._groupParams().setDiametre(24.);
		instrument._groupParams().setHasDbleBord(true);
		instrument._groupParams().setDbleBordCol(Color.CYAN);
		instrument._groupParams().setHasShadow(true);
		instrument._groupParams().setFillingCol(Color.MAGENTA);
		instrument._groupParams().setFillingStyle(FillingStyle.GRAD);
		instrument._groupParams().setGradColEnd(Color.RED);
		instrument._groupParams().setGradColStart(Color.YELLOW);
		instrument._groupParams().setHatchingsCol(Color.GRAY);
		instrument._groupParams().setLineStyle(LineStyle.DOTTED);
		instrument._groupParams().setShadowCol(Color.GREEN);
	}



	public void checkShape(@SuppressWarnings("unused") final IShape shape) {//FIXME
//		assertEquals(instrument.getLineColor(), shape.getLineColour());
//		assertEquals(instrument.getThickness(), shape.getThickness());
//		if(shape.isBordersMovable())
//			assertEquals(instrument.getBorderPosition(), shape.getBordersPosition());
//		if(shape instanceof IDot) {
//			assertEquals(instrument.getDotStyle(), ((IDot)shape).getDotStyle());
//			assertEquals(instrument.getDotSize(), ((IDot)shape).getRadius()*2.);
//		}
//		if(shape.isDbleBorderable()) {
//			assertEquals(instrument.isDoubleBorder(), shape.hasDbleBord());
//			assertEquals(instrument.getDoubleBorderColor(), shape.getDbleBordCol());
//		}
//		if(shape.isFillable()) {
//			assertEquals(instrument.getFillingColor(), shape.getFillingCol());
//
//			if(shape.isInteriorStylable()) {
//				assertEquals(instrument.getFillingStyle(), shape.getFillingStyle());
//				assertEquals(instrument.getGradEndColor(), shape.getGradColEnd());
//				assertEquals(instrument.getGradStartColor(), shape.getGradColStart());
//				assertEquals(instrument.getHatchingsColor(), shape.getHatchingsCol());
//			}
//		}
//
//		if(shape.isLineStylable())
//			assertEquals(instrument.getLineStyle(), shape.getLineStyle());
//		if(shape.isShadowable()) {
//			assertEquals(instrument.getShadowColor(), shape.getShadowCol());
//			assertEquals(instrument.isShadow(), shape.hasShadow());
//		}
	}
}

