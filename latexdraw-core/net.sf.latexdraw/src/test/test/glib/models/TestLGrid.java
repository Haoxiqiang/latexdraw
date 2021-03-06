package test.glib.models;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.latexdraw.glib.models.ShapeFactory;
import net.sf.latexdraw.glib.models.interfaces.shape.ICircle;
import net.sf.latexdraw.glib.models.interfaces.shape.IGrid;
import net.sf.latexdraw.glib.models.interfaces.shape.IPositionShape;
import net.sf.latexdraw.glib.models.interfaces.shape.IRectangle;
import net.sf.latexdraw.glib.models.interfaces.shape.IShape;
import net.sf.latexdraw.glib.models.interfaces.shape.IStandardGrid;

import org.junit.Before;
import org.junit.Test;

import test.HelperTest;
import test.glib.models.interfaces.TestIGrid;

public class TestLGrid extends TestIGrid<IGrid> {
	@Before
	public void setUp() {
		shape  = ShapeFactory.createGrid(false, ShapeFactory.createPoint());
		shape2 = ShapeFactory.createGrid(false, ShapeFactory.createPoint());
	}


	@Override
	@Test
	public void testIsTypeOf() {
		assertFalse(shape.isTypeOf(null));
		assertFalse(shape.isTypeOf(IRectangle.class));
		assertFalse(shape.isTypeOf(ICircle.class));
		assertTrue(shape.isTypeOf(IShape.class));
		assertTrue(shape.isTypeOf(IPositionShape.class));
		assertTrue(shape.isTypeOf(IStandardGrid.class));
		assertTrue(shape.isTypeOf(IGrid.class));
		assertTrue(shape.isTypeOf(shape.getClass()));
	}


	@Test
	public void testConstructors() {
		IGrid grid = ShapeFactory.createGrid(false, ShapeFactory.createPoint());
		assertTrue(grid.getGridEndX()>=grid.getGridStartX());
		assertTrue(grid.getGridEndY()>=grid.getGridStartY());
		HelperTest.assertEqualsDouble(0., grid.getPosition().getX());
		HelperTest.assertEqualsDouble(0., grid.getPosition().getY());
	}
}
