package test.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import net.sf.latexdraw.util.LNumber;

import org.junit.Test;

public class TestLNumber{
	@Test
	public void testEqualsEqual() {
		assertTrue(LNumber.equalsDouble(10, 0, 10));
		assertTrue(LNumber.equalsDouble(-10, 0, 10));
	}


	@Test
	public void testEquals1() {
		double a = 1./3.;
		double b = 0.333333333333333;

		assertTrue(LNumber.equalsDouble(a, b));
		b = 0.3299;
		assertFalse(LNumber.equalsDouble(a, b));

		a = -1./3.;
		b = -0.333333333333333;

		assertTrue(LNumber.equalsDouble(a, b));
		b = -0.3299;
		assertFalse(LNumber.equalsDouble(a, b));
	}



	@Test
	public void testEquals2() {
		double a = 1./3.;
		double b = 0.333333333333333;

		assertTrue(LNumber.equalsDouble(a, b, 0.00001));
		b = 0.3299;
		assertFalse(LNumber.equalsDouble(a, b, 0.00000001));
		assertTrue(LNumber.equalsDouble(a, b, 0.01));

		a = -1./3.;
		b = -0.333333333333333;

		assertTrue(LNumber.equalsDouble(a, b, 0.00001));
		b = -0.3299;
		assertFalse(LNumber.equalsDouble(a, b, 0.00000001));
		assertFalse(LNumber.equalsDouble(a, b, -0.00000001));
		assertFalse(LNumber.equalsDouble(a, b, 0.));
		assertTrue(LNumber.equalsDouble(a, b, 0.01));
	}



	@Test
	public void testGetCutNumber1() {
		double v = 0.333333333333333;

		assertEquals(v, LNumber.getCutNumber(v, 0.1), 0.0001);
		assertEquals(v, LNumber.getCutNumber(v, 0.2), 0.0001);
		assertEquals(v, LNumber.getCutNumber(v, 0.3), 0.0001);
		assertEquals(0., LNumber.getCutNumber(v, 0.34), 0.0001);

		v = 0.000001;
		assertEquals(v, LNumber.getCutNumber(v, 0.00000001), 0.0001);
		assertEquals(0., LNumber.getCutNumber(v, 0.00001), 0.0001);

		v = 1E-10;
		assertEquals(0., LNumber.getCutNumber(v, 0.00000001), 0.0001);
	}



	@Test
	public void testGetCutNumber2() {
		float v = 0.333333333333333f;

		assertEquals(v, LNumber.getCutNumber(v, 0.1), 0.0001);
		assertEquals(v, LNumber.getCutNumber(v, 0.2), 0.0001);
		assertEquals(v, LNumber.getCutNumber(v, 0.3), 0.0001);
		assertEquals(0f, LNumber.getCutNumber(v, 0.34), 0.0001);

		v = 0.000001f;
		assertEquals(v, LNumber.getCutNumber(v, 0.00000001), 0.0001);
		assertEquals(0f, LNumber.getCutNumber(v, 0.00001), 0.0001);

		v = 1E-10f;
		assertEquals(0f, LNumber.getCutNumber(v, 0.00000001), 0.0001);
	}
}
