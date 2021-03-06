package test.svg;

import static org.junit.Assert.assertEquals;
import net.sf.latexdraw.parsers.svg.SVGComment;
import net.sf.latexdraw.parsers.svg.SVGDocument;

import org.junit.Test;
import org.w3c.dom.Node;

public class TestSVGComment extends TestSVGText {
	@Override
	@Test
	public void testGetNodeType() {
		SVGComment cdata = createSVGText("test", doc);
		assertEquals(Node.COMMENT_NODE, cdata.getNodeType());
	}


	@Override
	protected SVGComment createSVGText(String txt, SVGDocument document) throws IllegalArgumentException {
		return new SVGComment(txt, document);
	}
}
