package test.glib.models.interfaces;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import net.sf.latexdraw.glib.models.interfaces.prop.IArcProp.ArcStyle;

import org.junit.Test;

public class TestArcStyle {
	@Test
	public void testSupportArrow() {
		assertFalse(ArcStyle.CHORD.supportArrow());
		assertFalse(ArcStyle.WEDGE.supportArrow());
		assertTrue(ArcStyle.ARC.supportArrow());
	}


	@Test
	public void testGetLabel() {
		assertNotNull(ArcStyle.ARC.getLabel());
		assertTrue(ArcStyle.ARC.getLabel().length()>0);
		assertNotNull(ArcStyle.CHORD.getLabel());
		assertTrue(ArcStyle.CHORD.getLabel().length()>0);
		assertNotNull(ArcStyle.WEDGE.getLabel());
		assertTrue(ArcStyle.WEDGE.getLabel().length()>0);
	}
}
